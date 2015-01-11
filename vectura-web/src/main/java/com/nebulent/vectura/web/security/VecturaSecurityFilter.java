package com.nebulent.vectura.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import com.nebulent.vectura.web.security.enums.AuthHeaders;

/**
 * {@link org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter}
 */
public class VecturaSecurityFilter extends GenericFilterBean {

	AuthenticationManager authenticationManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


		try {

			SecurityContextHolder.getContext().setAuthentication(
					authenticationManager
							.authenticate(
									new PreAuthenticatedAuthenticationToken(
											new VecturaPrincipal(
													((HttpServletRequest) request).getHeader(AuthHeaders.API_KEY.toString())
											),
											new VecturaCredentials(
													((HttpServletRequest) request).getHeader(AuthHeaders.SIGNATURE.toString()),
													((HttpServletRequest) request).getHeader(AuthHeaders.TIMESTAMP.toString())
											)
									)
							)
			);

		} catch (AuthenticationException exc) {
			SecurityContextHolder.clearContext();
			request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exc);
		}

		chain.doFilter(request, response);
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
}
