package com.nebulent.vectura.data.model.mongodb.core;

public enum AddressTypeEnum {

	MAILING("MAIL", "Mailing"),
	BILLING("BILL", "Billing");
	
	private String code;
	private String description;
	
	/**
	 * @param code
	 * @param description
	 * @param sort
	 */
	private AddressTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	/**
	 * @param code
	 */
	private AddressTypeEnum(String code){
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param code
	 * @return
	 */
	public static AddressTypeEnum fromString(String code) {
		if (code != null) {
			for (AddressTypeEnum e : AddressTypeEnum.values()) {
				if (code.equalsIgnoreCase(e.getCode())) {
					return e;
				}
			}
		}
		return null;
	}
}
