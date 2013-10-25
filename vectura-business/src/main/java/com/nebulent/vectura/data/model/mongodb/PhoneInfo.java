/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;


/**
 * @author mfedorov
 *
 */
public class PhoneInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929673447858828232L;
	
	private PhoneTypeEnum type = PhoneTypeEnum.WORK;
	private String value;
	
	/**
	 * @return the type
	 */
	public PhoneTypeEnum getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(PhoneTypeEnum type) {
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
