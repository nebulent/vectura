/**
 * 
 */
package com.nebulent.vectura.web.security.mule;

import org.apache.cxf.interceptor.security.AuthenticationException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.security.Authentication;
import org.mule.api.security.SecurityContext;
import org.mule.api.security.SecurityException;
import org.mule.api.security.SecurityManager;
import org.mule.api.security.SecurityProviderNotFoundException;
import org.mule.api.security.UnauthorisedException;
import org.mule.api.security.UnknownAuthenticationTypeException;
import org.mule.config.i18n.CoreMessages;
import org.mule.security.AbstractAuthenticationFilter;

import com.nebulent.vectura.services.AuthenticationService;

/**
 * @author mfedorov
 * 
 * Performs authentication based on a signature hash, api key and timestamp in format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'". 
 * All values are retrieved from the {@link MuleMessage} based on expressions specified via class setters. These
 * are then used to create a DefaultMuleAuthentication object which is passed to the authenticate method of the
 * {@link SecurityManager}.
**/
public class SignedRequestProcessingFilter extends AbstractAuthenticationFilter{
	
	private String apiKeyExpression = "#[header:inbound:Vectura-APIKey]";
    private String signatureExpression = "#[header:inbound:Vectura-Signature]";
    private String timestampExpression = "#[header:inbound:Vectura-Timestamp]";
    private String versionExpression = "#[header:inbound:Vectura-Version]";
    
    private AuthenticationService authenticationService;
    
    /**
     * 
     */
    public SignedRequestProcessingFilter(){super();}

    /**
     * Authenticates the current message.
     *
     * @param event the current message recieved
     * @throws org.mule.api.security.SecurityException if authentication fails
     */
    @Override
    public void authenticate(MuleEvent event)
        throws SecurityException, SecurityProviderNotFoundException, UnknownAuthenticationTypeException{
    	
    	ExpressionManager expressionManager = event.getMuleContext().getExpressionManager();
        
        Object apiKeyEval = expressionManager.evaluate(getApiKeyExpression(), event);
        Object timestampEval = expressionManager.evaluate(getTimestampExpression(), event);
        Object signatureEval = expressionManager.evaluate(getSignatureExpression(), event);
        
        if (apiKeyEval == null) {
            throw new UnauthorisedException(CoreMessages.authNoCredentials());
        }
        if (signatureEval == null) {
            throw new UnauthorisedException(CoreMessages.authNoCredentials());
        }
        if (timestampEval == null) {
            throw new UnauthorisedException(CoreMessages.authNoCredentials());
        }
        else if(!getAuthenticationService().getSignatureService().isValidTimestamp(timestampEval.toString())){
        	throw new UnauthorisedException(CoreMessages.valueIsInvalidFor(timestampEval.toString(), getTimestampExpression()));
        }

        try {
			getAuthenticationService().authenticate(apiKeyEval.toString(), timestampEval.toString(), signatureEval.toString());
		} 
        catch (AuthenticationException e) {
            if (logger.isDebugEnabled()){
                logger.debug("Authentication request for user: " + apiKeyEval.toString() + " failed: " + e.toString());
            }
            System.out.println("Authentication request for user: " + apiKeyEval.toString() + " failed: " + e.toString());
            throw new UnauthorisedException(CoreMessages.authFailedForUser(apiKeyEval.toString()), e);
		}
        
        //Authentication authResult = new DefaultMuleAuthentication(new MuleCredentials(apiKeyEval.toString(), signatureEval.toString().toCharArray()));

        org.springframework.security.core.Authentication springAuth = new SignedRequestAuthenticationToken(apiKeyEval.toString(), signatureEval.toString().toCharArray());
        Authentication authResult = new VecturaAuthentication(springAuth, null, event);
        
        // Authentication success
        if (logger.isDebugEnabled()){
            logger.debug("Authentication success: " + authResult.toString());
        }

        System.out.println("Authentication success: " + authResult.toString());
        
        SecurityContext context = getSecurityManager().createSecurityContext(authResult);
        context.setAuthentication(authResult);
        event.getSession().setSecurityContext(context);
    }

	/**
	 * @return the apiKeyExpression
	 */
	public String getApiKeyExpression() {
		return apiKeyExpression;
	}

	/**
	 * @param apiKeyExpression the apiKeyExpression to set
	 */
	public void setApiKeyExpression(String apiKeyExpression) {
		this.apiKeyExpression = apiKeyExpression;
	}

	/**
	 * @return the signatureExpression
	 */
	public String getSignatureExpression() {
		return signatureExpression;
	}

	/**
	 * @param signatureExpression the signatureExpression to set
	 */
	public void setSignatureExpression(String signatureExpression) {
		this.signatureExpression = signatureExpression;
	}

	/**
	 * @return the timestampExpression
	 */
	public String getTimestampExpression() {
		return timestampExpression;
	}

	/**
	 * @param timestampExpression the timestampExpression to set
	 */
	public void setTimestampExpression(String timestampExpression) {
		this.timestampExpression = timestampExpression;
	}

	/**
	 * @return the versionExpression
	 */
	public String getVersionExpression() {
		return versionExpression;
	}

	/**
	 * @param versionExpression the versionExpression to set
	 */
	public void setVersionExpression(String versionExpression) {
		this.versionExpression = versionExpression;
	}

	/**
	 * @return the authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService the authenticationService to set
	 */
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
}
