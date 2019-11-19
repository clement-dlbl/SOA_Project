
package fr.Project_SOA.Presence.Ressources;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fr.Project_SOA.Presence.Sensor.Presence_Sensor;
import fr.Project_SOA.Presence.oM2M_Client.Client;
import fr.Project_SOA.Presence.oM2M_Client.Response;
import obix.Obj;
import obix.io.ObixDecoder;




@RestController
public class Presence_Sensor_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		return "You are in room " + name;
	}
	
	@GetMapping("/rooms/{name}/sensors/presence")
	public Presence_Sensor Check_Window_Open(@PathVariable String name) {
		Presence_Sensor presence_sens = new Presence_Sensor(name, false);
		//return "The presence sensor is located in room " + name +"\n. His status is " + presence_sens.isPresence();
		return presence_sens;
	}
	
	@GetMapping("/test_OM2M/{name}")
	public String Retrieve_OM2M(@PathVariable String name) throws IOException, XPathExpressionException {
		Client client = new Client();
		
		Response resp = client.retrieve("http://127.0.0.1:8080/~/in-cse/cin-710869350", "admin:admin");
		
		// System.out.println(resp.getRepresentation());
		String utf8 = resp.getRepresentation().replace("&lt;", "<");
		utf8 = utf8.replace("&quot;", "\"");
		utf8 = utf8.replace("&gt;", ">");
		System.out.println(utf8);
		
		/* Cutting the string */
		int begin = utf8.indexOf("<obj>");
		int end = utf8.indexOf("</obj>");
		String obix_XML = utf8.substring(begin, end+6);
		
		
		System.out.println(obix_XML);
		
		/*using oBIX library*/
		Obj obj = ObixDecoder.fromString(obix_XML);
		
		System.out.println(obj.get("category") + " : " + obj.get("data"));
		String category = obj.get("category").toString();
		//int data = (int)obj.get("data").toString().atoi;
		System.out.println(category);
		
		return obj.get("category") + " : " + obj.get("data");
		
		
		/*
		XPathFactory xpf = XPathFactory.newInstance();
		XPath path = xpf.newXPath();
		Document xml = convertStringToXMLDocument(utf8);
		Element root = xml.getDocumentElement();
		String exp = "//obj";
		NodeList list = (NodeList)path.evaluate(exp, root, XPathConstants.NODE);
		
		for(int i = 0; i< list.getLength(); i++) {
			Node n = list.item(i);
			
			System.out.println(n.getNodeName() + " : " + n.getNodeValue());
		}
		
		//System.out.println(node.getNodeName() + " : " + node.getNodeValue());
		System.out.println(list.getLength());
		return resp.getRepresentation();*/
	}
	/*
	private static Document convertStringToXMLDocument(String xmlString) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
	*/
}