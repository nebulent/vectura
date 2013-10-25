package com.nebulent.vectura.services.resources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusResponse {

    public enum Status {

		SUCCESS("Success"),
		FAILURE("Failure");
		
		private String value;
	
		/**
		 * @param value
		 */
		private Status(String value) {
		    this.value = value;
		}
	
		/**
		 * @return
		 */
		public String getValue() {
		    return value;
		}
    }
    
    private Status status;
    private String code;
    private String itemizeId;
    
    /**
     * @return
     */
    public static StatusResponse success() {
    	return new StatusResponse(Status.SUCCESS);
    }

    /**
     * @return
     */
    public static StatusResponse error() {
    	return new StatusResponse(Status.FAILURE);
    }

    /**
     *
     */
    public StatusResponse() {
    }

    /**
     * @param status
     */
    public StatusResponse(boolean status) {
    	this.status = status ? Status.SUCCESS : Status.FAILURE;
    }

    /**
     * @param status
     */
    public StatusResponse(Status status) {
    	this.status = status;
    }

    /**
	 * @param status
	 * @param code
	 * @param itemizeId
	 */
	public StatusResponse(boolean status, String code, String itemizeId) {
		this(status);
		this.code = code;
		this.itemizeId = itemizeId;
	}

	/**
     * @return the status
     */
    public Status getStatus() {
    	return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
    	this.status = status;
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
	 * @return the itemizeId
	 */
	public String getItemizeId() {
		return itemizeId;
	}

	/**
	 * @param itemizeId the itemizeId to set
	 */
	public void setItemizeId(String itemizeId) {
		this.itemizeId = itemizeId;
	}
}
