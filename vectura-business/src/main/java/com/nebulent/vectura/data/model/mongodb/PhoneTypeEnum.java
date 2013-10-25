package com.nebulent.vectura.data.model.mongodb;

public enum PhoneTypeEnum {

	BUSINESS("B", "Business (Primary)", 0),
	WORK("W", "Work", 1),
	HOME("H", "Home", 2),
	CELL("C", "Cell", 3),
	FAX("F", "Fax", 4);
	
	private String code;
	private String description;
	private int sort;
	
	/**
	 * @param code
	 * @param description
	 * @param sort
	 */
	private PhoneTypeEnum(String code, String description, int sort) {
		this.code = code;
		this.description = description;
		this.sort = sort;
	}
	
	/**
	 * @param code
	 */
	private PhoneTypeEnum(String code){
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
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * @param code
	 * @return
	 */
	public static PhoneTypeEnum fromString(String code) {
		if (code != null) {
			for (PhoneTypeEnum e : PhoneTypeEnum.values()) {
				if (code.equalsIgnoreCase(e.getCode())) {
					return e;
				}
			}
		}
		return null;
	}
}
