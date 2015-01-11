package com.nebulent.vectura.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.WebAttributes;

public class VecturaAuthenticationEntryPoint implements AuthenticationEntryPoint {

	Logger logger = LoggerFactory.getLogger(VecturaAuthenticationEntryPoint.class);
	
	@Override
	public void commence(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception
			) throws IOException, ServletException {

		Object exc = request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		logger.error("Processing Authentication error", exc);

		if (exc instanceof AuthenticationCredentialsNotFoundException) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credentials missing");
		} else if (exc instanceof BadCredentialsException) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unexpected exception at authentications");
		}

	}

}
