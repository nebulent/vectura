/**
 * 
 */
package com.nebulent.vectura.web.security.cxf;

import java.util.Map;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Required;

public class AuthenticatorInterceptor extends AbstractPhaseInterceptor<Message> {

	private Map<String, String> users;

	@Required
	public void setUsers(Map<String, String> users) {
		this.users = users;
	}

	public AuthenticatorInterceptor() {
		super(Phase.RECEIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message
	 * .Message)
	 */
	public void handleMessage(Message message) {

		AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);

		if (policy == null) {
			System.out.println("User attempted to log in with no credentials");
			throw new RuntimeException("Denied");
		}

		String expectedPassword = users.get(policy.getUserName());
		if (expectedPassword == null
				|| !expectedPassword.equals(policy.getPassword())) {
			throw new RuntimeException("Denied");
		}
	}
}
