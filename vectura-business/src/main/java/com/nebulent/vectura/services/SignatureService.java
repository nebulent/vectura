/**
 * 
 */
package com.nebulent.vectura.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author mfedorov
 *
 */
public interface SignatureService {

	String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	String TIME_ZONE = "UTC";
	TimeZone timeZone = TimeZone.getTimeZone(TIME_ZONE);
	DateFormat dateFormatter = new SimpleDateFormat(TIMESTAMP_FORMAT);
	
	/**
	 * @param apiKey
	 * @param timestamp
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	String generateSignature(String apiKey, Calendar timestamp, String secretKey) throws Exception;
	
	/**
	 * @param apiKey
	 * @param timestamp
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	String generateSignature(String apiKey, String timestamp, String secretKey) throws Exception;
	
	/**
	 * @return
	 */
	Calendar generateTimestamp();
	
	/**
	 * @return
	 */
	String generateTimestamp(Calendar calendar);
	
	/**
	 * @param timestamp
	 * @return
	 */
	boolean isValidTimestamp(String timestamp);
}