package com.nea.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/inject")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemo {

	@GET
	@Path("/annotations")
	public String getParamUsingAnnotaions(@MatrixParam("matrix") String matrix,
								@HeaderParam("header") String header,
								@CookieParam("cookie") String cookie ) {
		return "matrix " + matrix + " header " + header + " cookie " + cookie;
	}
	
	@GET
	@Path("/contexts")
	public String getParamUsingContexts(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
		String path = uriInfo.getBaseUri().toString();
		String header = httpHeaders.getCookies().toString();
		return "path: " + path + " header: " + header;
	}
}
