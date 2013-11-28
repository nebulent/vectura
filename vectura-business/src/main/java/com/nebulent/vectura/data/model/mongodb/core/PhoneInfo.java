/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb.core;

import java.io.Serializable;


/**
 * @author mfedorov
 *
 */
public class PhoneInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929673447858828232L;
	
	private String type = PhoneTypeEnum.WORK.toString();
	private String value;
	
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
