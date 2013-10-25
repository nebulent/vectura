/**
 * 
 */
package com.nebulent.vectura.services.impl;

import org.apache.cxf.interceptor.security.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebulent.vectura.data.model.mongodb.Account;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;
import com.nebulent.vectura.services.AuthenticationService;
import com.nebulent.vectura.services.SignatureService;

/**
 * @author mfedorov
 *
 */
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
    private SignatureService signatureService;
	@Autowired
	private CoreRepository coreRepository;
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.security.services.AuthenticationService#authenticate(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void authenticate(String apiKey, String timestamp, String signature) throws AuthenticationException {
		Account account = coreRepository.getAccountRepository().findByApiKey(apiKey);
		if(account == null) throw new AuthenticationException("Invalid API key");
		try {
			String generatedSignature = signatureService.generateSignature(apiKey, timestamp, account.getSecretKey());
			if(!generatedSignature.equals(signature)){
				throw new AuthenticationException("Authentication denied");
			}
		} catch (Exception e) {
			throw new AuthenticationException("Authentication failed");
		}
	}

	/**
	 * @return the signatureService
	 */
	public SignatureService getSignatureService() {
		return signatureService;
	}

	/**
	 * @param signatureService the signatureService to set
	 */
	public void setSignatureService(SignatureService signatureService) {
		this.signatureService = signatureService;
	}

	/**
	 * @return the coreRepository
	 */
	public CoreRepository getCoreRepository() {
		return coreRepository;
	}

	/**
	 * @param coreRepository the coreRepository to set
	 */
	public void setCoreRepository(CoreRepository coreRepository) {
		this.coreRepository = coreRepository;
	}
}
