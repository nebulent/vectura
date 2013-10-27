/**
 * 
 */
package com.nebulent.vectura.services.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.nebulent.vectura.services.utils.DomainUtils;

/**
 * @author mfedorov
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/vectura-test.xml"})
public class GoogleGeocoderTest {

	@Autowired
	Geocoder geocoder;

	@Test
	public void testGeocoding(){
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("114 South 18th Street, Philadelphia, PA 19103").setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		List<GeocoderResult> results = geocoderResponse.getResults();
		for (GeocoderResult result : results) {
			System.out.println(DomainUtils.toAddressInfo(result));
		}
	}
}
