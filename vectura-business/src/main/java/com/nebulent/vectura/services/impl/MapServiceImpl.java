/**
 * 
 */
package com.nebulent.vectura.services.impl;

import java.util.List;

import nebulent.schema.software.vectura._1.AddressInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.nebulent.vectura.services.MapService;
import com.nebulent.vectura.services.utils.DomainUtils;

/**
 * @author mfedorov
 *
 */
public class MapServiceImpl implements MapService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Geocoder geocoder;
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.MapService#getLocationByAddress(java.lang.String)
	 */
	@Override
	public AddressInfo getLocationByAddress(String fullAddress) {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
		}
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(fullAddress).setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		List<GeocoderResult> results = geocoderResponse.getResults();
		for (GeocoderResult result : results) {
			//if(!result.isPartialMatch()){
				return DomainUtils.toAddress(DomainUtils.toAddressInfo(result));
			//}
			//else{
			//	logger.warn("Partial match:" + result);
			//}
		}
		logger.error("Failed to find address:" + fullAddress + " because " + geocoderResponse.getStatus());
		return null;
	}

	/**
	 * @return the geocoder
	 */
	public Geocoder getGeocoder() {
		return geocoder;
	}

	/**
	 * @param geocoder the geocoder to set
	 */
	public void setGeocoder(Geocoder geocoder) {
		this.geocoder = geocoder;
	}
}
