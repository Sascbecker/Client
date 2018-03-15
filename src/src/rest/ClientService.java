package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

import clientConnector.Singleton;
import clientConnector.ClientConnector;

@Path( "/{parameter: kontakt|getMessage}" )
public class ClientService
{
	
	@Path("/getMessage")//namensgebung aus sicht des clients ###
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean getMessage( @QueryParam("absender") String absender, @QueryParam("empfaenger") String empfaenger,
							@QueryParam("gruppenID") int gruppenID, @QueryParam("timestamp") int timestamp, 
							@QueryParam("message") String message, @QueryParam("aktion") int aktion ) {
		//ClientConnector.getMessage( absender );
		
		return true;//Nachricht angekommen
	}
}