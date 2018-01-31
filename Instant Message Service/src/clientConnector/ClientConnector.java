package clientConnector;


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
	
	public static String baseUrl = "http://localhost:4434";
	
	public static void sendMessage( String absender, String message ) {	//evtl boolean zuruckgegeben? //evtl doch absender senden?
		//String baseUrl = "http://localhost:4434";
		String webContextPath = "/sendMessage";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		Singleton.setInstanceAndMessage(message);
		
		//evtl rückgabewert interpretieren
		
		//String jsonObjectString = 
		target.path( webContextPath ).queryParam( "absender", absender );//.request( MediaType.APPLICATION_JSON ).get( String.class );
		
		//JsonObject jsonObject = jsonFromString(jsonObjectString);
		
		
		//weiterverarbeiten
		//der
		//Nachricht
		
		//und
		
		//evtl einlesen
		//in datenbank
		//bzw event erzeugen
		
		
		
		//System.out.println( jsonObject );
		
		//scanner.close();
	}
	
	public static void login( String name, String password ) {
		//String baseUrl = "http://localhost:4434";
		String webContextPath = "/login";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		Singleton.setInstanceAndMessage(password);
		
		//im besten fall boolean empfangen um zu interpretieren
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println(ip);
			System.out.println(ip.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

		String jsonObjectString = target.path( webContextPath )
				.queryParam( "name", name ).queryParam("password", password)
				.request( MediaType.APPLICATION_JSON ).get( String.class );
		
		JsonObject jsonObject = jsonFromString(jsonObjectString);
		
		
		if (jsonObject.getBoolean("ergebnis")) {
			//erfolg
			//evtl nach nachrichten fragen igendeine fkt callen
			//getMessage(name);
		} else {
			//password oder benutzername falsch
		}
		
		//;to be continued
	}
	
	public static void getMessage( String name ) {
		
		//String baseUrl = "http://localhost:4434";
		String webContextPath = "/getMessage";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		String jsonObjectString = target.path( webContextPath )
				.queryParam( "absender", name )
				.request( MediaType.APPLICATION_JSON )
				.get( String.class );
		
		JsonObject jsonObject = jsonFromString(jsonObjectString);
		String message = jsonObject.getString("Message");
		
		System.out.println(message);
		
		//EVENT HANLDER (EVENT WERFEN FÜR DARSTELLUNG ODER EVTL DATENBANK?) SASCHA FRAGEN

	}
	/*
	public static void getMessage() {
		
		//String baseUrl = "http://localhost:4434";
		String webContextPath = "/getMessage";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		String jsonObjectString = target.path( webContextPath )
				.queryParam( "absender", name )
				.request( MediaType.APPLICATION_JSON )
				.get( String.class );
		
		JsonObject jsonObject = jsonFromString(jsonObjectString);
		String message = jsonObject.getString("Message");
		
		System.out.println(message);
		
		//EVENT HANLDER (EVENT WERFEN FÜR DARSTELLUNG ODER EVTL DATENBANK?) SASCHA FRAGEN

	}*/
	
	public static void addContact( String absender, String empfaenger ) {
		String webContextPath = "/kontakte";

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		target.path( webContextPath )
		.queryParam( "absender", absender )
		.queryParam( "empfaenger", empfaenger )
		.queryParam( "aktion", 1 );
	}
	
	public static void removeContact( String absender, String empfaenger ) {
		String webContextPath = "/kontakte";

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		target.path( webContextPath )
		.queryParam( "absender", absender )
		.queryParam( "empfaenger", empfaenger )
		.queryParam( "aktion", 2 );
	}
	
	public static void blockContact( String absender, String empfaenger ) {
		String webContextPath = "/kontakte";

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		target.path( webContextPath )
		.queryParam( "absender", absender )
		.queryParam( "empfaenger", empfaenger )
		.queryParam( "aktion", 3 );
	}
	
	private static JsonObject jsonFromString(String jsonObjectStr) {
	    JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
	    JsonObject object = jsonReader.readObject();
	    jsonReader.close();
	    return object;
	}
	
}