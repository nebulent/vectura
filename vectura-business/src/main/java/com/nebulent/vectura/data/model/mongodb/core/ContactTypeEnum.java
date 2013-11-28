package com.nebulent.vectura.data.model.mongodb.core;

public enum ContactTypeEnum {

	PRIMARY("PRI", "Primary"),
	SECONDARY("SEC", "Secondary"),
	BILLING("BILL", "Billing");
	
	private String code;
	private String description;
	
	/**
	 * @param code
	 * @param description
	 * @param sort
	 */
	private ContactTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	/**
	 * @param code
	 */
	private ContactTypeEnum(String code){
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
	public static ContactTypeEnum fromString(String code) {
		if (code != null) {
			for (ContactTypeEnum e : ContactTypeEnum.values()) {
				if (code.equalsIgnoreCase(e.getCode())) {
					return e;
				}
			}
		}
		return null;
	}
}
