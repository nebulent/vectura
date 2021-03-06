/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mfedorov
 *
 */
public class Contact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8315861775376416810L;
	
	private String type = ContactTypeEnum.PRIMARY.toString();
	private String fName;
	private String lName;
	private String email;
	private List<PhoneInfo> phoneInfos = new ArrayList<PhoneInfo>();
	private List<AddressInfo> addressInfos = new ArrayList<AddressInfo>();
	
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
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}
	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phoneInfos
	 */
	public List<PhoneInfo> getPhoneInfos() {
		return phoneInfos;
	}
	/**
	 * @return the addressInfos
	 */
	public List<AddressInfo> getAddressInfos() {
		return addressInfos;
	}
}
