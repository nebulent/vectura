package com.nebulent.vectura.services.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: danielhonig
 * Date: 8/19/13
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserNotFoundException extends WebApplicationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8518938271157349514L;

	public UserNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND).
                entity(message).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
