package com.nebulent.vectura.web.security.enums;

/**
 *
 */
public enum AuthHeaders {

	API_KEY("Vectura-ApiKey"),
	SIGNATURE("Vectura-Signature"),
	TIMESTAMP("Vectura-Timestamp"),
	VERSION("Vectura-Version");

	private String name;

	AuthHeaders(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
