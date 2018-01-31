package clientServer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

import clientConnector.Singleton;
import clientConnector.ClientConnector;

@Path( "/{parameter: requestMessage|getMessage}" )
public class ClientService
{
	@Path("/requestMessage")
   	@GET
   	@Produces( MediaType.APPLICATION_JSON )
   	public String sendMessage() {
   		JsonObjectBuilder builder = Json.createObjectBuilder();
   		
   		builder.add("Message", Singleton.getMessage());
		
		return builder.build().toString();
	}
	//außerdem uebertragung der gruppen id
	//
	
	//kontakt
	
	@Path("/getMessage")//namensgebung aus sicht des clients ###
	@GET
	public void getMessage( @QueryParam("absender") String absender) {
		ClientConnector.getMessage( absender );
	}
}