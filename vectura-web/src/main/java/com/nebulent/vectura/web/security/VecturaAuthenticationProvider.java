package com.nebulent.vectura.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.nebulent.vectura.services.AuthenticationService;

public class VecturaAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	AuthenticationService authenticationService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			if (authentication.getPrincipal() == null || authentication.getCredentials() == null) {
				throw new AuthenticationCredentialsNotFoundException("Principal missing");
			}

			VecturaPrincipal principal = (VecturaPrincipal) authentication.getPrincipal();
			VecturaCredentials credentials = (VecturaCredentials) authentication.getCredentials();
			authenticationService
					.authenticate(
							principal.getName(),
							credentials.getSignature(),
							credentials.getTimestamp()
					);

			return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(), null, AuthorityUtils.NO_AUTHORITIES);
		} 
		catch (AuthenticationException e) {
			throw new BadCredentialsException("Authentication failed");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		/**
		 * support any type of Authenttication objects
		 * should inspect the restriction on principal
		 * and credentials
		 */
		return Authentication.class.isAssignableFrom(clazz);
	}

	/**
	 * @return the authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService the authenticationService to set
	 */
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
