package fr.Project_SOA.Master.PublishSubscribe.Test;
import java.io.IOException;

import fr.Project_SOA.Master.PublishSubscribe.Client.Client;
import fr.Project_SOA.Master.PublishSubscribe.Util.RequestLoader;


public class Main {

	public static void main(String[] args) throws IOException {
		
		Client client = new Client();
		String Representation = RequestLoader.getRequestFromFile("create_ae.xml");
		client.retrieve("http://localhost:8080/~/in-cse", "admin:admin");
		client.create("http://localhost:8080/~/in-cse", Representation, "admin:admin", "ty=2");	
		
//		String Representation_update = RequestLoader.getRequestFromFile("create_ae_new.xml");
//		client.update("http://127.0.0.1:8080/~/mn-cse/mn-name/AE_NAME", Representation_update, "admin:admin");
		
		
//		String Representation_cnt = RequestLoader.getRequestFromFile("create_cnt.xml");
//		client.create("http://127.0.0.1:8080/~/mn-cse/mn-name/AE_NAME/?rcn=0", Representation_cnt, "admin:admin", "ty=3");	
//		client.retrieve("http://127.0.0.1:8080/~/mn-cse/mn-name/AE_NAME/?rcn=5", "admin:admin");
		
//		String Representation_cin = RequestLoader.getRequestFromFile("create_cin.xml");
//		client.create("http://127.0.0.1:8080/~/mn-cse/mn-name/AE_NAME/test/?rcn=0", Representation_cin, "admin:admin", "ty=4");
//		client.retrieve("http://127.0.0.1:8080/~/mn-cse/mn-name/AE_NAME/test/?rcn=5", "admin:admin");
		
//		client.delete("http://127.0.0.1:8080/~/mn-cse/mn-name/AE_NAME", "admin:admin");
		
	}

}