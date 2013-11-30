/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb.core;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.services.utils.DomainUtils;

/**
 * @author mfedorov
 *
 */
@Document
public class AddressInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3795727939168566836L;
	
	private String type = AddressTypeEnum.MAILING.toString();
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String stateOrProvince;
	private String zipCode;
	private String countryCode = "US";
	private String name;
	@Indexed(unique=true)
	private String hash;
	
	@Transient
	private double[] location;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
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
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 */
	public void hash(){
		hash = DomainUtils.getDigest(toString());
	}
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.data.model.mongodb.BaseEntity#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(256);
		builder.append(getAddressLine1().toUpperCase().trim());
		if(StringUtils.isNotBlank(getAddressLine2())){
			builder.append("|").append(getAddressLine2().toUpperCase().trim());
		}
		if(StringUtils.isNotBlank(getAddressLine3())){
			builder.append("|").append(getAddressLine3().toUpperCase().trim());
		}
		if(StringUtils.isNotBlank(getCity())){
			builder.append("|").append(getCity().toUpperCase().trim());
		}
		builder.append("|").append(getStateOrProvince().toUpperCase().trim())
		.append("|").append(getZipCode().trim())
		.append("|").append(getCountryCode().toUpperCase().trim());
		
		return builder.toString();
	}
	
}
