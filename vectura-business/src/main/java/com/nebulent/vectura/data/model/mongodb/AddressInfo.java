/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author mfedorov
 *
 */
public class AddressInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3795727939168566836L;
	
	private AddressTypeEnum type;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String stateOrProvince;
	private String zipCode;
	private String countryCode = "US";
	
	@Indexed
	private String hash;
	
	@GeoSpatialIndexed
	private double[] location;
	
	/**
	 * @return the type
	 */
	public AddressTypeEnum getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(AddressTypeEnum type) {
		this.type = type;
	}
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	/**
	 * @return the addressLine3
	 */
	public String getAddressLine3() {
		return addressLine3;
	}
	/**
	 * @param addressLine3 the addressLine3 to set
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the stateOrProvince
	 */
	public String getStateOrProvince() {
		return stateOrProvince;
	}
	/**
	 * @param stateOrProvince the stateOrProvince to set
	 */
	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @return the location
	 */
	public double[] getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(double[] location) {
		this.location = location;
	}
	
	/**
	 * 
	 */
	public void hash(){
		StringBuilder builder = new StringBuilder(256);
		builder.append(getAddressLine1().toUpperCase().trim());
		if(StringUtils.isNotBlank(getAddressLine2())){
			builder.append("|").append(getAddressLine2().toUpperCase().trim());
		}
		if(StringUtils.isNotBlank(getAddressLine3())){
			builder.append("|").append(getAddressLine3().toUpperCase().trim());
		}
		builder.append("|").append(getCity().toUpperCase().trim())
		.append("|").append(getStateOrProvince().toUpperCase().trim())
		.append("|").append(getZipCode().toUpperCase().trim())
		.append("|").append(getCountryCode().toUpperCase().trim());
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(builder.toString().getBytes());
			hash = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			hash = String.valueOf(builder.hashCode());
		}
	}
}