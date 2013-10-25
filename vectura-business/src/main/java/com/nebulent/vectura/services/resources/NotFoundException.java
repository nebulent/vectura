package com.nebulent.vectura.services.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 */
public class NotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3022004161842860211L;

	public NotFoundException(StatusResponse message) {
        super(Response.status(Response.Status.NOT_FOUND).
                entity(message).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
