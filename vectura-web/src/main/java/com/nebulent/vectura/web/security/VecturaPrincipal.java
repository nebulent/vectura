package com.nebulent.vectura.web.security;

import org.springframework.security.core.CredentialsContainer;

import java.security.Principal;

/**
 * Created by weaver on 11/25/14.
 */
public class VecturaPrincipal implements CredentialsContainer, Principal {


	private String apiKey;

	public VecturaPrincipal(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public void eraseCredentials() {
		this.apiKey = null;
	}

	@Override
	public String getName() {
		return this.apiKey;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VecturaPrincipal)) return false;

		VecturaPrincipal that = (VecturaPrincipal) o;

		if (!apiKey.equals(that.apiKey)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return apiKey.hashCode();
	}

	/**
	 * this class is intended as
	 * a simple String uuid token
	 * @return this.apiKey of type {@link java.lang.String}
	 */
	@Override
	public String toString() {
		return this.apiKey;
	}
}
