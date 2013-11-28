package com.nebulent.vectura.data.model.mongodb.core;

public enum PhoneTypeEnum {

	BUSINESS("BUSINESS", 0),
	WORK("WORK", 1),
	HOME("HOME", 2),
	CELL("CELL", 3),
	FAX("WORK", 4);
	
	private String code;
	private int sort;
	
	/**
	 * @param code
	 * @param description
	 * @param sort
	 */
	private PhoneTypeEnum(String code, int sort) {
		this.code = code;
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
