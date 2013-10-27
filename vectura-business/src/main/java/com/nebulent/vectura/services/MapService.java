/**
 * 
 */
package com.nebulent.vectura.services;

import nebulent.schema.software.vectura._1.AddressInfo;

/**
 * @author mfedorov
 *
 */
public interface MapService {

	/**
	 * @param address
	 * @return
	 */
	public AddressInfo getLocationByAddress(String fullAddress);
}
