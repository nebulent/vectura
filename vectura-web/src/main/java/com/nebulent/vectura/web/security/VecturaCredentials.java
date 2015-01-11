package com.nebulent.vectura.web.security;

import org.springframework.security.core.CredentialsContainer;

/**
 * Created by weaver on 11/25/14.
 */
public class VecturaCredentials implements CredentialsContainer {


	private String timestamp;

	private String signature;

	public VecturaCredentials(String signature, String timestamp) {
		this.signature = signature;
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getSignature() {
		return signature;
	}

	@Override
	public void eraseCredentials() {
		this.timestamp = null;
		this.signature = null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VecturaCredentials)) return false;

		VecturaCredentials that = (VecturaCredentials) o;

		if (!signature.equals(that.signature)) return false;
		if (!timestamp.equals(that.timestamp)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = timestamp.hashCode();
		result = 31 * result + signature.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "VecturaCredentials{" +
				"timestamp='" + timestamp + '\'' +
				", signature='" + signature + '\'' +
				'}';
	}
}
