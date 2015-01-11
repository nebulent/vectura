package com.nebulent.vectura.web.exception;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by weaver on 11/28/14.
 */
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

	Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

	@Override
	public Response toResponse(Exception exception) {


		logger.error("", exception);
/*

		Authentication exceptions will not be handled here

		if (exception instanceof AuthenticationCredentialsNotFoundException) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
		}
*/


		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
