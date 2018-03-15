package rest;


import javax.json.*;
import javax.json.JsonObject;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
//import java.util.*;

public class ClientConnector
{
		
		//theoretische uebergabeparameter: absender, empfaenger, absender-id, empfaenger-id, timestamp, ...?
	
	private static String baseUrl = "http://localhost:4434";
	
	public static boolean sendMessage( String absender, String empfaenger, int gruppenID, int timestamp, String message, int aktion ) throws InterruptedException {
		boolean angekommen = false;
		
		String webContextPath = "/getMessage";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		angekommen = target.path( webContextPath )
					.queryParam( "absender", absender )
					.queryParam( "empfaenger", empfaenger )
					.queryParam( "gruppenID", gruppenID )
					.queryParam( "timestamp", timestamp )
					.queryParam( "message", message )
					.queryParam( "aktion", aktion )
					.request(MediaType.APPLICATION_JSON).get(boolean.class);
		
		return angekommen;
	}
	
	public static boolean login( String name, String password ) {
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
				.queryParam( "name", name ).queryParam("password", password).queryParam( "ip", ip )
				.request( MediaType.APPLICATION_JSON ).get(boolean.class);
		
		return true;
		//;to be continued
	}
}