package com.nebulent.vectura.services;

import org.apache.cxf.interceptor.security.AuthenticationException;

public interface AuthenticationService {

	/**
	 * @param apiKey
	 * @param timestamp
	 * @param signature
	 * @throws AuthenticationException
	 */
	public void authenticate(String apiKey, String timestamp, String signature) throws AuthenticationException;
	
	/**
	 * @return
	 */
	public SignatureService getSignatureService();
}
