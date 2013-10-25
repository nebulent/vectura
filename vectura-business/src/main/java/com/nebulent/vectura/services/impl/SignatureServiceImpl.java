/**
 * 
 */
package com.nebulent.vectura.services.impl;

import java.text.ParseException;
import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.cxf.common.util.Base64Utility;
import org.springframework.util.Assert;

import com.nebulent.vectura.services.SignatureService;

/**
 * @author mfedorov
 *
 */
public class SignatureServiceImpl implements SignatureService {

	/**
	 * 
	 */
	public SignatureServiceImpl() {
		dateFormatter.setTimeZone(timeZone);
	}
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.security.services.SignatureService#generateSignature(java.lang.String, java.util.Calendar, java.lang.String)
	 */
	@Override
	public String generateSignature(String apiKey, Calendar timestamp, String secretKey) throws Exception {
		return generateSignature(apiKey, generateTimestampAsString(timestamp), secretKey);
	}
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.security.services.SignatureService#generateSignature(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String generateSignature(String apiKey, String timestamp, String secretKey) throws Exception {
		Assert.hasText(apiKey);
		Assert.hasText(timestamp);
		Assert.hasText(secretKey);
		return generateSignatureFor(apiKey, timestamp, secretKey);
	}
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.security.services.SignatureService#generateTimestamp()
	 */
	@Override
	public Calendar generateTimestamp() {
		Calendar timestamp = Calendar.getInstance();
		timestamp.setTimeZone(timeZone);
		return timestamp;
	}
	
    /* (non-Javadoc)
	 * @see com.nebulent.vectura.security.services.SignatureService#isValidTimestamp(java.lang.String)
	 */
	@Override
	public synchronized boolean isValidTimestamp(String timestamp) {
		try {
			dateFormatter.parse(timestamp);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
     * @param calendar
     * @return
     */
    private synchronized String generateTimestampAsString(Calendar calendar) {
        return dateFormatter.format(calendar.getTime());
    }
    
    /**
     * @param apiKey
     * @param timestamp
     * @param secretKey
     * @return
     * @throws Exception
     */
    private String generateSignatureFor(String apiKey, String timestamp, String secretKey) throws Exception {
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(new SecretKeySpec(secretKey.getBytes(), HMAC_SHA1_ALGORITHM));
		return Base64Utility.encode(mac.doFinal((apiKey + timestamp).getBytes()));
	}
}