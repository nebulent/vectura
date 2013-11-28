package com.nebulent.vectura.data.model.mongodb.core;

public enum UserTypeEnum {

	USER("USR", "User"),
	ADMIN("ADM", "Admin"),
	DRIVER("DRVR", "Driver");
	
	private String code;
	private String description;
	
	/**
	 * @param code
	 * @param description
	 * @param sort
	 */
	private UserTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	/**
	 * @param code
	 */
	private UserTypeEnum(String code){
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
	public static UserTypeEnum fromString(String code) {
		if (code != null) {
			for (UserTypeEnum e : UserTypeEnum.values()) {
				if (code.equalsIgnoreCase(e.getCode())) {
					return e;
				}
			}
		}
		return null;
	}
}
