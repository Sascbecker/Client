package rest;


//import javax.json.*;
//import javax.json.JsonObject;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

//import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import dataclass.*;
//import java.util.*;

public class ClientConnector
{
		
		//theoretische uebergabeparameter: absender, empfaenger, absender-id, empfaenger-id, timestamp, ...?
	
	private static String baseUrl = "http://localhost:4434";
	
	public static boolean sendMessage( Message nachricht ) throws InterruptedException {
		boolean angekommen = false;
		
		String webContextPath = "/getMessage";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		angekommen = target.path( webContextPath )
					.queryParam( "nachricht", nachricht )
					//.queryParam( "empfaenger", empfaenger )
					//.queryParam( "gruppenID", gruppenID )
					//.queryParam( "timestamp", timestamp )
					//.queryParam( "message", message )
					//.queryParam( "aktion", aktion )
					.request(MediaType.APPLICATION_JSON).get(boolean.class);
		
		return angekommen;
	}
	
	public static boolean login( User user ) {
		//String baseUrl = "http://localhost:4434";
		String webContextPath = "/login";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		InetAddress ip = null;
		
		try {
			ip = InetAddress.getLocalHost();
			System.out.println(ip);
			System.out.println(ip.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		boolean angekommen = target.path( webContextPath )
				.queryParam( "user", user ).queryParam( "ip", ip )
				.request( MediaType.APPLICATION_JSON ).get(boolean.class);
		
		return angekommen;
		//;to be continued
	}
	
	public static boolean sendGroup( Group gruppe ) {
		String webContextPath = "/getGroup";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		//Group gruppe = new Group(aktion, sender, name);
		
		boolean angekommen = target.path( webContextPath ).queryParam( "gruppe", gruppe )
				.request( MediaType.APPLICATION_JSON ).get(boolean.class);
		
		return angekommen;
		//;to be continued
	}
}