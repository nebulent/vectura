package com.nebulent.vectura.data.model.mongodb;

public enum RunTypeEnum {

	PICKUP("PU", "Pickup"),
	DROPOFF("DO", "Drop-off");
	
	private String code;
	private String description;
	
	/**
	 * @param code
	 * @param description
	 * @param sort
	 */
	private RunTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	/**
	 * @param code
	 */
	private RunTypeEnum(String code){
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
	public static RunTypeEnum fromString(String code) {
		if (code != null) {
			for (RunTypeEnum e : RunTypeEnum.values()) {
				if (code.equalsIgnoreCase(e.getCode())) {
					return e;
				}
			}
		}
		return null;
	}
}
