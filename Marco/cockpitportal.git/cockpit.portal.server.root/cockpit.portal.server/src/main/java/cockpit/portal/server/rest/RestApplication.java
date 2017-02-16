package cockpit.portal.server.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@ApplicationPath("portal")
public class RestApplication extends Application {

//	private Set<Object> singletons = new HashSet<>();
//	
//	public RestApplication() {
//		CorsFilter corsFilter = new CorsFilter();
//		corsFilter.getAllowedOrigins().add("*");
//		corsFilter.setAllowedHeaders("Accept, Content-type");
//		corsFilter.setAllowCredentials(true);
//		corsFilter.setAllowedMethods("GET, POST, PUT, DELETE, OPTIONS");
//		
//		singletons.add(corsFilter);
//	}
//
//	@Override
//	public Set<Object> getSingletons() {
//		return super.getSingletons();
//	}

	@OPTIONS
//	@Path("{path:.*}")
	public Response options() {
		 return Response.status(Response.Status.NO_CONTENT).build();
		
//		return Response
//				.status(Response.Status.NO_CONTENT)
//				.header("Access-Control-Allow-Origin", "null")
//				.header("Access-Control-Allow-Headers", "accept, content-type")
//				.header("Access-Control-Allow-Credentials", "true")
//				.header("Access-Control-Allow-Methods",
//						"GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}

}