package com.nebulent.vectura.services.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 */
public class AlreadyExistsException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8450803107132154449L;

	public AlreadyExistsException(StatusResponse message) {
        super(Response.status(Response.Status.CONFLICT).
                entity(message).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
