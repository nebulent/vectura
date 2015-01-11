package com.nebulent.vectura.web.service.rest;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 */
@CrossOriginResourceSharing()
@Path("/security")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SecurityService {

	@GET
	public Response sayHi() {
		return Response.ok("hey").build();
	}

}
