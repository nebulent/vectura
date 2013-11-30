package com.nebulent.vectura.data.model.mongodb;

import java.util.Date;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.data.model.mongodb.core.AddressInfo;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;

/**
 * @author mfedorov
 *
 */
@Document(collection=CoreRepository.COLLECTION_RIDES)
@CompoundIndexes({
    @CompoundIndex(name = "dailylist_idx", def = "{'accountUuid': 1, 'rideDateAsString': 1, 'apptOn': 1}")
})
public class Ride extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2941734474504285146L;

	private String accountUuid;
	private String rideDateAsString;
	
	private String extTripId;
	private Date apptOn;
	private Date pickupOn;
	private int addnlRdrs;
	private Double mileage;
	private Double price;
	private String vehicleType;
	private String specReq;
	private String notes;
	
	private AddressInfo pickupAddr;
	private AddressInfo dropoffAddr;
	
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
	 * @return the date
	 */
	public String getRideOn() {
		return getRideDateAsString();
	}
	/**
	 * @return the date
	 */
	public String getRideDateAsString() {
		return rideDateAsString;
	}
	/**
	 * @param date the date to set
	 */
	public void setRideOn(String date) {
		setRideDateAsString(date);
	}
	/**
	 * @param date the date to set
	 */
	public void setRideDateAsString(String date) {
		this.rideDateAsString = date;
	}
	/**
	 * @return the extTripId
	 */
	public String getExtTripId() {
		return extTripId;
	}
	/**
	 * @param extTripId the extTripId to set
	 */
	public void setExtTripId(String extTripId) {
		this.extTripId = extTripId;
	}
	/**
	 * @return the apptOn
	 */
	public Date getApptOn() {
		return apptOn;
	}
	/**
	 * @param apptOn the apptOn to set
	 */
	public void setApptOn(Date apptOn) {
		this.apptOn = apptOn;
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
	 * @return the addnlRdrs
	 */
	public int getAddnlRdrs() {
		return addnlRdrs;
	}
	/**
	 * @param addnlRdrs the addnlRdrs to set
	 */
	public void setAddnlRdrs(int addnlRdrs) {
		this.addnlRdrs = addnlRdrs;
	}
	/**
	 * @return the mileage
	 */
	public Double getMileage() {
		return mileage;
	}
	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 * @return the specReq
	 */
	public String getSpecReq() {
		return specReq;
	}
	/**
	 * @param specReq the specReq to set
	 */
	public void setSpecReq(String specReq) {
		this.specReq = specReq;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the pickupAddr
	 */
	public AddressInfo getPickupAddr() {
		return pickupAddr;
	}
	/**
	 * @param pickupAddr the pickupAddr to set
	 */
	public void setPickupAddr(AddressInfo pickupAddr) {
		this.pickupAddr = pickupAddr;
	}
	/**
	 * @return the dropoffAddr
	 */
	public AddressInfo getDropoffAddr() {
		return dropoffAddr;
	}
	/**
	 * @param dropoffAddr the dropoffAddr to set
	 */
	public void setDropoffAddr(AddressInfo dropoffAddr) {
		this.dropoffAddr = dropoffAddr;
	}
}
