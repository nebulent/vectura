/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import java.util.Date;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.persistence.mongodb.CoreRepository;

/**
 * @author mfedorov
 *
 */
@Document(collection=CoreRepository.COLLECTION_RUNS)
@CompoundIndexes({
    @CompoundIndex(name = "dailylist_idx", def = "{'accountUuid': 1, 'driverUuid': 1, 'pickupOn': 1}")
})
public class Run extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9072090033323173648L;

	private String accountUuid;
	private String driverUuid;
	private Date pickupOn;
	private String pickupPlaceUuid;
	private String dropoffPlaceUuid;
	
	/**
	 * @return the accountUuid
	 */
	public String getAccountUuid() {
		return accountUuid;
	}
	/**
	 * @param accountUuid the accountUuid to set
	 */
	public void setAccountUuid(String customerUuid) {
		this.accountUuid = customerUuid;
	}
	/**
	 * @return the driverUuid
	 */
	public String getDriverUuid() {
		return driverUuid;
	}
	/**
	 * @param driverUuid the driverUuid to set
	 */
	public void setDriverUuid(String driverUuid) {
		this.driverUuid = driverUuid;
	}
	/**
	 * @return the pickupOn
	 */
	public Date getPickupOn() {
		return pickupOn;
	}
	/**
	 * @param pickupOn the pickupOn to set
	 */
	public void setPickupOn(Date pickupOn) {
		this.pickupOn = pickupOn;
	}
	/**
	 * @return the pickupPlaceUuid
	 */
	public String getPickupPlaceUuid() {
		return pickupPlaceUuid;
	}
	/**
	 * @param pickupPlaceUuid the pickupPlaceUuid to set
	 */
	public void setPickupPlaceUuid(String pickupPlaceUuid) {
		this.pickupPlaceUuid = pickupPlaceUuid;
	}
	/**
	 * @return the dropoffPlaceUuid
	 */
	public String getDropoffPlaceUuid() {
		return dropoffPlaceUuid;
	}
	/**
	 * @param dropoffPlaceUuid the dropoffPlaceUuid to set
	 */
	public void setDropoffPlaceUuid(String dropoffPlaceUuid) {
		this.dropoffPlaceUuid = dropoffPlaceUuid;
	}
}
