/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.persistence.mongodb.CoreRepository;

/**
 * @author mfedorov
 *
 */
@Document(collection=CoreRepository.COLLECTION_VEHICLES)
public class Vehicle extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886761175614951646L;
	
	@Id
	private String vin;
	@Indexed
	private String accountUuid;
	
	private VehicleTypeEnum type = VehicleTypeEnum.VAN;
	private int seats = 4;
	private int year;
	private String make;
	private String model;
	private String color;
	
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
	 * @return the type
	 */
	public VehicleTypeEnum getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(VehicleTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * @param seats the seats to set
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
}
