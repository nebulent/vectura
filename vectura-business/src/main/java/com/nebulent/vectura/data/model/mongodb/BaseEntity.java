/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Max Fedorov
 *
 */
@Document
public abstract class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2898027558386059700L;
	
	/**
	 * 
	 */
	public BaseEntity() {
	}

	@Id
	private String uuid;
	private boolean disabled = false;
	
	// Change history data.
	private String addedBy;
	private Date addedOn;
	private String updatedBy;
	private Date updatedOn;
	
	/**
	 * @param uuid
	 */
	public BaseEntity(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 
	 */
	public void identity(){
		uuid = UUID.randomUUID().toString();
	}
	
	/**
	 * @return
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the addedBy
	 */
	public String getAddedBy() {
		return addedBy;
	}

	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	/**
	 * @return the addedOn
	 */
	public Date getAddedOn() {
		return addedOn;
	}

	/**
	 * @param addedOn the addedOn to set
	 */
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * 
	 */
	public void clearIdentity() {
		uuid = null;
	}
	
	/**
	 * @return
	 */
	public boolean isNew() {
		return StringUtils.isBlank(uuid);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	} 
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}