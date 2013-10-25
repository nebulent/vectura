/**
 * 
 */
package com.nebulent.vectura.web.security.mule;

import java.util.Map;

import org.mule.api.MuleEvent;
import org.mule.module.spring.security.SpringAuthenticationAdapter;
import org.springframework.security.core.Authentication;

/**
 * @author mfedorov
 *
 */
public class VecturaAuthentication extends SpringAuthenticationAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6487443793267909557L;

	/**
	 * @param authentication
	 */
	public VecturaAuthentication(Authentication authentication) {
		super(authentication);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param authentication
	 * @param properties
	 */
	public VecturaAuthentication(Authentication authentication,
			Map<String, Object> properties) {
		super(authentication, properties);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param authentication
	 * @param properties
	 * @param event
	 */
	public VecturaAuthentication(Authentication authentication,
			Map<String, Object> properties, MuleEvent event) {
		super(authentication, properties, event);
		// TODO Auto-generated constructor stub
	}

}
