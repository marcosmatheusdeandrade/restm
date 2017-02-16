package br.linx.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@ApplicationPath("rest")
public class RestApp extends Application{

	@OPTIONS
	public Response options() {
		 return Response.status(Response.Status.NO_CONTENT).build();
	}
	
}
