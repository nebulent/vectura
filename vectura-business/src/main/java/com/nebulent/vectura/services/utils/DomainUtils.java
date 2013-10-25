/**
 * 
 */
package com.nebulent.vectura.services.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.nebulent.vectura.data.model.mongodb.Account;
import com.nebulent.vectura.data.model.mongodb.AddressInfo;
import com.nebulent.vectura.data.model.mongodb.Contact;
import com.nebulent.vectura.data.model.mongodb.ContactTypeEnum;
import com.nebulent.vectura.data.model.mongodb.Patient;
import com.nebulent.vectura.data.model.mongodb.PhoneInfo;
import com.nebulent.vectura.data.model.mongodb.PhoneTypeEnum;
import com.nebulent.vectura.data.model.mongodb.User;
import com.nebulent.vectura.data.model.mongodb.Vehicle;

/**
 * @author mfedorov
 *
 */
public final class DomainUtils {

	/**
	 * @param accountType
	 * @return
	 */
	public static Account toAccount(nebulent.schema.software.vectura._1.Account accountType){
		Account account = new Account();
		account.setEin(accountType.getEin());
		account.setName(accountType.getName());
		account.setApiKey(accountType.getApiKey());
		account.setSecretKey(new String(accountType.getSecretKey()));
		
		for(nebulent.schema.software.vectura._1.PhoneInfo phoneInfo: accountType.getPhones()){
			account.getPhones().add(toPhone(phoneInfo));
		}
		
		for(nebulent.schema.software.vectura._1.AddressInfo addressInfo: accountType.getAddresses()){
			account.getAddresses().add(toAddress(addressInfo));
		}
		
		for(nebulent.schema.software.vectura._1.ContactType contactInfo: accountType.getContacts()){
			account.getContacts().add(toContact(contactInfo));
		}
		
		for(nebulent.schema.software.vectura._1.User user: accountType.getUsers()){
			account.getUsers().add(toUser(user));
		}
		
		for(nebulent.schema.software.vectura._1.Patient patient: accountType.getPatients()){
			account.getPatients().add(toPatient(patient));
		}
		return account;
	}
	
	/**
	 * @param accountType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.Account toAccount(Account accountType){
		nebulent.schema.software.vectura._1.Account account = new nebulent.schema.software.vectura._1.Account();
		account.setEin(accountType.getEin());
		account.setName(accountType.getName());
		account.setApiKey(accountType.getApiKey());
		account.setSecretKey(accountType.getSecretKey().getBytes());
		
		for(PhoneInfo phoneInfo: accountType.getPhones()){
			account.getPhones().add(toPhone(phoneInfo));
		}
		
		for(AddressInfo addressInfo: accountType.getAddresses()){
			account.getAddresses().add(toAddress(addressInfo));
		}
		
		for(Contact contactInfo: accountType.getContacts()){
			account.getContacts().add(toContact(contactInfo));
		}
		
		for(User user: accountType.getUsers()){
			account.getUsers().add(toUser(user));
		}
		
		for(Patient patient: accountType.getPatients()){
			account.getPatients().add(toPatient(patient));
		}
		
		return account;
	}
	
	/**
	 * @param vehicleType
	 * @return
	 */
	public static Vehicle toVehicle(nebulent.schema.software.vectura._1.Vehicle vehicleType){
		Vehicle vehicle = new Vehicle();
		vehicle.setColor(vehicleType.getColor());
		vehicle.setAccountUuid(vehicleType.getAccountId());
		vehicle.setMake(vehicleType.getMake());
		vehicle.setModel(vehicleType.getModel());
		vehicle.setSeats(vehicleType.getSeats());
		vehicle.setYear(vehicleType.getYear());
		vehicle.setVin(vehicleType.getVin());
		return vehicle;
	}
	
	/**
	 * @param vehicleType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.Vehicle toVehicleType(Vehicle vehicleType){
		nebulent.schema.software.vectura._1.Vehicle vehicle = new nebulent.schema.software.vectura._1.Vehicle();
		vehicle.setColor(vehicleType.getColor());
		vehicle.setAccountId(vehicleType.getAccountUuid());
		vehicle.setMake(vehicleType.getMake());
		vehicle.setModel(vehicleType.getModel());
		vehicle.setSeats(vehicleType.getSeats());
		vehicle.setYear(vehicleType.getYear());
		vehicle.setVin(vehicleType.getVin());
		return vehicle;
	}
	
	/**
	 * @param contactType
	 * @return
	 */
	public static Contact toContact(nebulent.schema.software.vectura._1.ContactType contactType){
		Contact contact = new Contact();
		contact.setEmail(contactType.getEmail());
		contact.setfName(contactType.getFirstName());
		contact.setlName(contactType.getLastName());
		contact.setType(ContactTypeEnum.fromString(contactType.getType()));
		
		for(nebulent.schema.software.vectura._1.PhoneInfo phoneInfo: contactType.getPhones()){
			contact.getPhoneInfos().add(toPhone(phoneInfo));
		}
		
		for(nebulent.schema.software.vectura._1.AddressInfo addressInfo: contactType.getAddresses()){
			contact.getAddressInfos().add(toAddress(addressInfo));
		}
		
		return contact;
	}
	
	/**
	 * @param contactType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.ContactType toContact(Contact contactType){
		nebulent.schema.software.vectura._1.ContactType contact = new nebulent.schema.software.vectura._1.ContactType();
		contact.setEmail(contactType.getEmail());
		contact.setFirstName(contactType.getfName());
		contact.setLastName(contactType.getlName());
		contact.setType(contactType.getType().getCode());
		
		for(PhoneInfo phoneInfo: contactType.getPhoneInfos()){
			contact.getPhones().add(toPhone(phoneInfo));
		}
		
		for(AddressInfo addressInfo: contactType.getAddressInfos()){
			contact.getAddresses().add(toAddress(addressInfo));
		}
		
		return contact;
	}
	
	/**
	 * @param addressType
	 * @return
	 */
	public static AddressInfo toAddress(nebulent.schema.software.vectura._1.AddressInfo addressType){
		AddressInfo address = new AddressInfo();
		address.setAddressLine1(addressType.getAddressLine1());
		address.setAddressLine2(addressType.getAddressLine2());
		address.setAddressLine3(addressType.getAddressLine3());
		address.setCity(addressType.getCity());
		address.setStateOrProvince(addressType.getStateOrProvince());
		address.setZipCode(addressType.getZipCode());
		if(addressType.getLat() != null && addressType.getLon() != null){
			address.setLocation(new double[]{addressType.getLat().doubleValue(), addressType.getLon().doubleValue()});
		}
		return address;
	}
	
	/**
	 * @param addressType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.AddressInfo toAddress(AddressInfo addressType){
		nebulent.schema.software.vectura._1.AddressInfo address = new nebulent.schema.software.vectura._1.AddressInfo();
		address.setAddressLine1(addressType.getAddressLine1());
		address.setAddressLine2(addressType.getAddressLine2());
		address.setAddressLine3(addressType.getAddressLine3());
		address.setCity(addressType.getCity());
		address.setStateOrProvince(addressType.getStateOrProvince());
		address.setZipCode(addressType.getZipCode());
		if(addressType.getLocation() != null && addressType.getLocation().length == 2){
			address.setLat(new BigDecimal(addressType.getLocation()[0]));
			address.setLon(new BigDecimal(addressType.getLocation()[1]));
		}
		return address;
	}
	
	/**
	 * @param phoneType
	 * @return
	 */
	public static PhoneInfo toPhone(nebulent.schema.software.vectura._1.PhoneInfo phoneType){
		PhoneInfo phone = new PhoneInfo();
		phone.setType(PhoneTypeEnum.fromString(phoneType.getType()));
		phone.setValue(phoneType.getValue());
		return phone;
	}
	
	/**
	 * @param phoneType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.PhoneInfo toPhone(PhoneInfo phoneType){
		nebulent.schema.software.vectura._1.PhoneInfo phone = new nebulent.schema.software.vectura._1.PhoneInfo();
		phone.setType(phoneType.getType().getCode());
		phone.setValue(phoneType.getValue());
		return phone;
	}
	
	/**
	 * @param patientType
	 * @return
	 */
	public static Patient toPatient(nebulent.schema.software.vectura._1.Patient patientType){
		Patient patient = new Patient();
		patient.setSsn(patientType.getSsn());
		patient.setEmail(patientType.getEmail());
		patient.setfName(patientType.getFirstName());
		patient.setlName(patientType.getLastName());
		patient.setType(ContactTypeEnum.fromString(patientType.getType()));
		
		for(nebulent.schema.software.vectura._1.PhoneInfo phoneInfo: patientType.getPhones()){
			patient.getPhoneInfos().add(toPhone(phoneInfo));
		}
		
		for(nebulent.schema.software.vectura._1.AddressInfo addressInfo: patientType.getAddresses()){
			patient.getAddressInfos().add(toAddress(addressInfo));
		}
		
		return patient;
	}
	
	/**
	 * @param patientType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.Patient toPatient(Patient patientType){
		nebulent.schema.software.vectura._1.Patient patient = new nebulent.schema.software.vectura._1.Patient();
		patient.setSsn(patientType.getSsn());
		patient.setEmail(patientType.getEmail());
		patient.setFirstName(patientType.getfName());
		patient.setLastName(patientType.getlName());
		patient.setType(patientType.getType().getCode());
		
		for(PhoneInfo phoneInfo: patientType.getPhoneInfos()){
			patient.getPhones().add(toPhone(phoneInfo));
		}
		
		for(AddressInfo addressInfo: patientType.getAddressInfos()){
			patient.getAddresses().add(toAddress(addressInfo));
		}
		
		return patient;
	}
	
	/**
	 * @param userType
	 * @return
	 */
	public static User toUser(nebulent.schema.software.vectura._1.User userType){
		User patient = new User();
		patient.setUsername(userType.getUsername());
		patient.setPwdHash(new String(userType.getPasswordHash()));
		patient.setEmail(userType.getEmail());
		patient.setfName(userType.getFirstName());
		patient.setlName(userType.getLastName());
		patient.setType(ContactTypeEnum.fromString(userType.getType()));
		
		for(nebulent.schema.software.vectura._1.PhoneInfo phoneInfo: userType.getPhones()){
			patient.getPhoneInfos().add(toPhone(phoneInfo));
		}
		
		for(nebulent.schema.software.vectura._1.AddressInfo addressInfo: userType.getAddresses()){
			patient.getAddressInfos().add(toAddress(addressInfo));
		}
		
		return patient;
	}
	
	/**
	 * @param userType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.User toUser(User userType){
		nebulent.schema.software.vectura._1.User user = new nebulent.schema.software.vectura._1.User();
		user.setUsername(userType.getUsername());
		user.setPasswordHash(userType.getPwdHash().getBytes());
		user.setLastLogin(toCalendar(userType.getLastLogin()));
		user.setEmail(userType.getEmail());
		user.setFirstName(userType.getfName());
		user.setLastName(userType.getlName());
		user.setType(userType.getType().getCode());
		
		for(PhoneInfo phoneInfo: userType.getPhoneInfos()){
			user.getPhones().add(toPhone(phoneInfo));
		}
		
		for(AddressInfo addressInfo: userType.getAddressInfos()){
			user.getAddresses().add(toAddress(addressInfo));
		}
		
		return user;
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(Date date){
		if(date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
