/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.data.model.mongodb.core.AddressInfo;
import com.nebulent.vectura.data.model.mongodb.core.Contact;
import com.nebulent.vectura.data.model.mongodb.core.NameValuePair;
import com.nebulent.vectura.data.model.mongodb.core.Patient;
import com.nebulent.vectura.data.model.mongodb.core.PhoneInfo;
import com.nebulent.vectura.data.model.mongodb.core.User;
import com.nebulent.vectura.data.model.mongodb.core.UserTypeEnum;
import com.nebulent.vectura.data.model.mongodb.core.Vehicle;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;

/**
 * @author mfedorov
 *
 */
@Document(collection=CoreRepository.COLLECTION_ACCOUNTS)
@CompoundIndexes({
    @CompoundIndex(name = "vehicle_vin_idx", def = "{'vehicles.vin': 1}"),
    @CompoundIndex(name = "address_hash_idx", def = "{'addresses.hash': 1}"),
    @CompoundIndex(name = "patients_ssn_idx", def = "{'patients.ssn': 1}"),
    @CompoundIndex(name = "users_username_idx", def = "{'users.username': 1}")
})
public class Account extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3965878523990681062L;

	private String name;
	private String ein;
	
	private List<AddressInfo> addresses = new ArrayList<AddressInfo>();
	private List<PhoneInfo> phones = new ArrayList<PhoneInfo>();
	private List<Contact> contacts = new ArrayList<Contact>();
	
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private List<User> users = new ArrayList<User>();
	private List<Patient> patients = new ArrayList<Patient>();
	private List<String> placeUuids = new ArrayList<String>();
	private Set<NameValuePair> settings = new HashSet<NameValuePair>();
	
	@Indexed (unique=true)
	private String apiKey;
	private String secretKey;
	private Date secretKeyResetOn;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the ein
	 */
	public String getEin() {
		return ein;
	}
	/**
	 * @param ein the ein to set
	 */
	public void setEin(String ein) {
		this.ein = ein;
	}
	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}
	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secret) {
		this.secretKey = secret;
	}
	/**
	 * @return the secretKeyResetOn
	 */
	public Date getSecretKeyResetOn() {
		return secretKeyResetOn;
	}
	/**
	 * @param secretKeyResetOn the secretKeyResetOn to set
	 */
	public void setSecretKeyResetOn(Date secretResetOn) {
		this.secretKeyResetOn = secretResetOn;
	}
	/**
	 * @return the addresses
	 */
	public List<AddressInfo> getAddresses() {
		return addresses;
	}
	/**
	 * @return the phones
	 */
	public List<PhoneInfo> getPhones() {
		return phones;
	}
	/**
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}
	/**
	 * @return the vehicles
	 */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * @return the drivers
	 */
	public List<User> getDrivers() {
		List<User> drivers = new ArrayList<User>();
		for (User user : getUsers()) {
			if(UserTypeEnum.DRIVER.toString().equalsIgnoreCase(user.getType())){
				drivers.add(user);
			}
		}
		return drivers;
	}
	/**
	 * @return the patients
	 */
	public List<Patient> getPatients() {
		return patients;
	}
	/**
	 * @return the locUuids
	 */
	public List<String> getPlaceUuids() {
		return placeUuids;
	}
	/**
	 * @return the settings
	 */
	public Set<NameValuePair> getSettings() {
		return settings;
	}
}
