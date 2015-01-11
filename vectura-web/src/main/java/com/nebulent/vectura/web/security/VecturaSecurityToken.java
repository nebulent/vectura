package com.nebulent.vectura.web.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class VecturaSecurityToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 322477192678031638L;

	private VecturaPrincipal principal;
	private VecturaCredentials credentials;

	/**
	 * constructor using details map
	 * @param details:java.util.Map
	 */
	public VecturaSecurityToken(Object details) {
		/**
		 * by default no authorities AbstractAuthenticationToken
		 * computes to an unauthorized Authentication
		 */
		super(AuthorityUtils.NO_AUTHORITIES);
		super.setDetails(details);
	}

	/**
	 * creates the authentication token
	 * sets the authorities and
	 * sets the internal isAuthenticated flag to true
	 * @param principal:org.springframework.security.core.CredentialsContainer
	 * @param credentials:org.springframework.security.core.CredentialsContainer
	 * @param authorities:java.util.Collection<? extends GrantedAuthority>
	 */
	public VecturaSecurityToken(
			VecturaPrincipal principal,
			VecturaCredentials credentials,
			Collection<? extends GrantedAuthority> authorities
	) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	/**
	 *
	 * @return credentials:org.springframework.security.core.CredentialsContainer
	 */
	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	/**
	 *
	 * @return principal:org.springframework.security.core.CredentialsContainer
	 */
	@Override
	public Object getPrincipal() {
		return this.principal;
	}
}
