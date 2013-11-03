/**
 * 
 */
package com.nebulent.vectura.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import nebulent.schema.software.vectura._1.AddressInfo;

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
