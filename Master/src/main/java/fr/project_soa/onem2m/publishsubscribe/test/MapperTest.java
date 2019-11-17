package fr.project_soa.onem2m.publishsubscribe.test;

import org.eclipse.om2m.commons.resource.AE;

import fr.project_soa.onem2m.publishsubscribe.mapper.Mapper;
import fr.project_soa.onem2m.publishsubscribe.mapper.MapperInterface;

public class MapperTest {

	public static void main(String[] args) {
		MapperInterface mapper = new Mapper();

		// example to test marshal operation
		AE ae = new AE();
		ae.setRequestReachability(false);
		
		ae.setAEID("222");
		ae.setAppID("aaa");
		ae.setAppName("test"); 
		
		String xml= mapper.marshal(ae);
		
		System.out.println(xml);
		

		
		// get the XML representation, parse it with unmarshal operation
		
		AE ae2 = (AE)mapper.unmarshal(xml);
		
		System.out.println(ae2.getAEID());
		System.out.println(ae2.getAppID());
		System.out.println(ae2.getAppName());
		
		
	
		
	}
	
}
