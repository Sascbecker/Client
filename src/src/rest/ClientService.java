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
							@QueryParam("gruppenID") int gruppenID, @QueryParam("timestamp") long timestamp, 
							@QueryParam("message") String message, @QueryParam("aktion") int aktion ) {
		//ClientConnector.getMessage( absender );
		
		return true;//Nachricht angekommen
	}
	
	@Path("/ping")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean ping() {
		return true;
	}
	
	@Path("/kontakte")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean kontakte( @QueryParam ( "kontakte") Kontakte kontakte ) {
		
		//kontakte weitergeben
		
		return true;
	}
	
	@Path("/lastMessage")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public void kontakte() {
		
		
		//sendlastmessagetimestamp
	}
}