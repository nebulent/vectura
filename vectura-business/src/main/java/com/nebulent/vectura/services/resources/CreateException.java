package com.nebulent.vectura.services.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public class CreateException extends WebApplicationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9004932313320200781L;

	
	public CreateException(Object content) {
        super(Response.status(Response.Status.CREATED).
                entity(content).type(MediaType.APPLICATION_XML).build());
    }

}
