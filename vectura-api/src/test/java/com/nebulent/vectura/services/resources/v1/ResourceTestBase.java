package com.nebulent.vectura.services.resources.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 * @author mfedorov
 *
 */
public class ResourceTestBase {
	
	/**
	 * @param address
	 * @param username
	 * @param password
	 * @param resource
	 * @return
	 */
	protected <T> T instantiateClient(String address, String username, String password, Class<T> resource) {
		return instantiateClient(address, username, password, resource, true, null);
	}
	
	/**
	 * @param address
	 * @param username
	 * @param password
	 * @param resource
	 * @param json
	 * @param headers
	 * @return
	 */
	protected <T> T instantiateClient(String address, String username, String password, Class<T> resource, boolean json, Map<String, String> headers) {
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();       
        bean.setAddress(address);
        bean.setServiceClass(resource);
        bean.setUsername(username);
        bean.setPassword(password);
        if(headers != null && !headers.isEmpty()){
        	bean.setHeaders(headers);
        }
        
        //Adding JSON converter
        if(json){
			JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
			jacksonJsonProvider.enable(JsonParser.Feature.ALLOW_COMMENTS, true);
	        bean.setProvider(jacksonJsonProvider);
        }
        
        
        //Adding logging(not necessary)
        List<AbstractFeature> features = new ArrayList<AbstractFeature>();
        features.add(new LoggingFeature());
        bean.setFeatures(features);
        
        //Getting user list
        return bean.create(resource);
	}
}
