package com.nebulent.vectura.services.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BadRequestException extends WebApplicationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1042222411081623524L;

	public BadRequestException(StatusResponse message) {
        super(Response.status(Response.Status.BAD_REQUEST).
                entity(message).type(MediaType.APPLICATION_JSON_TYPE).build());
    }

}
