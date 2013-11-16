/**
 * 
 */
package com.nebulent.vectura.services.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nebulent.schema.software.vectura._1.Ride;
import nebulent.schema.software.vectura._1.Run;
import nebulent.schema.software.vectura._1.VehicleTypeEnum;

import org.apache.commons.lang.StringUtils;

import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderResult;
import com.nebulent.vectura.data.model.mongodb.Account;
import com.nebulent.vectura.data.model.mongodb.AddressInfo;
import com.nebulent.vectura.data.model.mongodb.Contact;
import com.nebulent.vectura.data.model.mongodb.ContactTypeEnum;
import com.nebulent.vectura.data.model.mongodb.Location;
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
		
		for(nebulent.schema.software.vectura._1.Vehicle vehicle: accountType.getVehicles()){
			account.getVehicles().add(toVehicle(vehicle));
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
		
		for(Vehicle vehicle: accountType.getVehicles()){
			account.getVehicles().add(toVehicleType(vehicle));
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
		address.setCountryCode(addressType.getCountryCode());
		address.setName(addressType.getName());
		if(addressType.getLat() != null && addressType.getLon() != null){
			address.setLocation(new double[]{addressType.getLon().doubleValue(), addressType.getLat().doubleValue()});
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
		address.setCountryCode(addressType.getCountryCode());
		address.setName(addressType.getName());
		if(addressType.getLocation() != null && addressType.getLocation().length == 2){
			address.setLon(new BigDecimal(addressType.getLocation()[0]));
			address.setLat(new BigDecimal(addressType.getLocation()[1]));
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
	 * @param locationType
	 * @return
	 */
	public static Location toLocation(nebulent.schema.software.vectura._1.Location locationType){
		Location location = new Location();
		location.setName(locationType.getName());
		location.setAddress(toAddress(locationType.getAddress()));
		return location;
	}
	
	/**
	 * @param locationType
	 * @return
	 */
	public static nebulent.schema.software.vectura._1.Location toLocation(Location locationType){
		nebulent.schema.software.vectura._1.Location location = new nebulent.schema.software.vectura._1.Location();
		location.setName(locationType.getName());
		location.setAddress(toAddress(locationType.getAddress()));
		return location;
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
	 * @param dbRide
	 * @return
	 */
	public static Ride toRide(com.nebulent.vectura.data.model.mongodb.Ride dbRide){
		Ride ride = new Ride();
		ride.setId(dbRide.getUuid());
		ride.setAccountId(dbRide.getAccountUuid());
		ride.setAdditionalRiders(dbRide.getAddnlRdrs());
		ride.setAppointmentOn(toCalendar(dbRide.getApptOn()));
		ride.setDateAsString(dbRide.getDate());
		ride.setExtTripId(dbRide.getExtTripId());
		ride.setMileage(dbRide.getMileage());
		ride.setNotes(dbRide.getNotes());
		ride.setPickupOn(toCalendar(dbRide.getPickupOn()));
		ride.setPrice(dbRide.getPrice());
		ride.setSpecRequirements(dbRide.getSpecReq());
		ride.setStatus(dbRide.getStatus());
		ride.setVehicleType(VehicleTypeEnum.fromValue(dbRide.getVehicleType()));
		ride.setVersion(dbRide.getVersion());
		ride.setPickupAddress(toAddress(dbRide.getPickupAddr()));
		ride.setDropOffAddress(toAddress(dbRide.getDropoffAddr()));
		return ride;
	}
	
	/**
	 * @param rideType
	 * @return
	 */
	public static com.nebulent.vectura.data.model.mongodb.Ride toRide(Ride rideType){
		com.nebulent.vectura.data.model.mongodb.Ride ride = new com.nebulent.vectura.data.model.mongodb.Ride();
		if(StringUtils.isNotBlank(rideType.getId())){
		ride.setUuid(rideType.getId());
		}
		ride.setAccountUuid(rideType.getAccountId());
		ride.setAddnlRdrs(rideType.getAdditionalRiders());
		ride.setApptOn(fromCalendar(rideType.getAppointmentOn()));
		ride.setDate(rideType.getDateAsString());
		ride.setExtTripId(rideType.getExtTripId());
		ride.setMileage(rideType.getMileage());
		ride.setNotes(rideType.getNotes());
		ride.setPickupOn(fromCalendar(rideType.getPickupOn()));
		ride.setPrice(rideType.getPrice());
		ride.setSpecReq(rideType.getSpecRequirements());
		ride.setStatus(rideType.getStatus());
		ride.setVehicleType(rideType.getVehicleType().toString());
		ride.setVersion(rideType.getVersion());
		ride.setPickupAddr(toAddress(rideType.getPickupAddress()));
		ride.setDropoffAddr(toAddress(rideType.getDropOffAddress()));
		return ride;
	}
	
	/**
	 * @param dbLocations
	 * @return
	 */
	public static List<nebulent.schema.software.vectura._1.Location> toLocations(List<Location> dbLocations){
		List<nebulent.schema.software.vectura._1.Location> locations = new ArrayList<nebulent.schema.software.vectura._1.Location>(dbLocations.size());
		for (Location dbLocation : dbLocations) {
			locations.add(toLocation(dbLocation));
		}
		return locations;
	}
	
	/**
	 * @param dbRides
	 * @return
	 */
	public static List<Ride> toRides(List<com.nebulent.vectura.data.model.mongodb.Ride> dbRides){
		List<Ride> rides = new ArrayList<Ride>(dbRides.size());
		for (com.nebulent.vectura.data.model.mongodb.Ride dbRide : dbRides) {
			rides.add(toRide(dbRide));
		}
		return rides;
	}
	
	/**
	 * @param result
	 * @return
	 */
	public static AddressInfo toAddressInfo(GeocoderResult result){
		AddressInfo addressInfo = new AddressInfo();
		if(result.getGeometry() != null && result.getGeometry().getLocation() != null){
			addressInfo.setLocation(new double[]{result.getGeometry().getLocation().getLng().doubleValue(), result.getGeometry().getLocation().getLat().doubleValue()});
		}
		
		String street_number = null;
		String street_address = null;
		String route = null;
		String administrative_area_level_1 = null;
		String locality = null;
		String postal_code = null;
		String country = null;
		String premise = null;
		String colloquial_area = null;
		List<GeocoderAddressComponent> components = result.getAddressComponents();
		for (GeocoderAddressComponent component : components) {
			String addressType = component.getTypes().get(0);
			if(StringUtils.isBlank(street_number) && "street_number".equalsIgnoreCase(addressType)){
				street_number = component.getLongName();
			}
			else if(StringUtils.isBlank(street_address) && "street_address".equalsIgnoreCase(addressType)){
				street_address = component.getLongName();
			}
			else if(StringUtils.isBlank(route) && "route".equalsIgnoreCase(addressType)){
				route = component.getLongName();
			}
			else if(StringUtils.isBlank(locality) && "locality".equalsIgnoreCase(addressType)){
				locality = component.getLongName();
			}
			else if(StringUtils.isBlank(administrative_area_level_1) && "administrative_area_level_1".equalsIgnoreCase(addressType)){
				administrative_area_level_1 = component.getLongName();
			}
			else if(StringUtils.isBlank(postal_code) && "postal_code".equalsIgnoreCase(addressType)){
				postal_code = component.getLongName();
			}
			else if(StringUtils.isBlank(country) && "country".equalsIgnoreCase(addressType)){
				country = component.getLongName();
			}
			else if(StringUtils.isBlank(premise) && "premise".equalsIgnoreCase(addressType)){
				premise = component.getLongName();
			}
			else if(StringUtils.isBlank(colloquial_area) && "colloquial_area".equalsIgnoreCase(addressType)){
				colloquial_area = component.getLongName();
			}
			else{
				System.out.println(addressType + "=" + component.getLongName());
			}
		}
		
		if(StringUtils.isNotBlank(premise)){
			addressInfo.setName(premise);
		}
		else{
			if(StringUtils.isNotBlank(colloquial_area)){
				addressInfo.setName(colloquial_area);
			}
		}
		System.out.println("Biz name:" + addressInfo.getName());
		if(StringUtils.isNotBlank(street_address)){
			addressInfo.setAddressLine1(street_address);
		}
		else{
			if(StringUtils.isNotBlank(street_number)){
				addressInfo.setAddressLine1(street_number);
			}
			if(StringUtils.isNotBlank(route)){
				if(StringUtils.isNotBlank(addressInfo.getAddressLine1())){
					addressInfo.setAddressLine1(addressInfo.getAddressLine1() + " " + route);
				}
				else{
					addressInfo.setAddressLine1(route);
				}
			}
		}
		if(StringUtils.isNotBlank(administrative_area_level_1)){
			addressInfo.setStateOrProvince(administrative_area_level_1);
		}
		if(StringUtils.isNotBlank(locality)){
			addressInfo.setCity(locality);
		}
		if(StringUtils.isNotBlank(postal_code)){
			addressInfo.setZipCode(postal_code);
		}
		if(StringUtils.isNotBlank(country)){
			addressInfo.setCountryCode(country);
		}
		return addressInfo;
	}
	
	/**
	 * @param run
	 * @return
	 */
	public static Run toRun(com.nebulent.vectura.data.model.mongodb.Run dbRun) {
		Run run = new Run();
		run.setId(dbRun.getUuid());
		run.setAccountId(dbRun.getAccountUuid());
		run.setDriverId(dbRun.getDriverUuid());
		run.setPickupOn(toCalendar(dbRun.getPickupOn()));
		if(StringUtils.isNotBlank(dbRun.getLocUuid())){
			nebulent.schema.software.vectura._1.Location loc = new nebulent.schema.software.vectura._1.Location();
			loc.setId(dbRun.getLocUuid());
			run.setStartLocation(loc);
		}
		if(StringUtils.isNotBlank(dbRun.getFinLocUuid())){
			nebulent.schema.software.vectura._1.Location loc = new nebulent.schema.software.vectura._1.Location();
			loc.setId(dbRun.getFinLocUuid());
			run.setFinalLocation(loc);
		}
		run.setStatus(dbRun.getStatus());
		run.setVersion(dbRun.getVersion());
		return run;
	}
	
	/**
	 * @param run
	 * @return
	 */
	public static com.nebulent.vectura.data.model.mongodb.Run toRun(Run runType) {
		com.nebulent.vectura.data.model.mongodb.Run run = new com.nebulent.vectura.data.model.mongodb.Run();
		if(StringUtils.isNotBlank(runType.getId())){
			run.setUuid(runType.getId());
		}
		run.setAccountUuid(runType.getAccountId());
		run.setDriverUuid(runType.getDriverId());
		run.setPickupOn(fromCalendar(runType.getPickupOn()));
		if(runType.getStartLocation() != null){
			run.setLocUuid(runType.getStartLocation().getId());
		}
		if(runType.getFinalLocation() != null){
			run.setFinLocUuid(runType.getFinalLocation().getId());
		}
		run.setStatus(runType.getStatus());
		run.setVersion(runType.getVersion());
		return run;
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
	
	/**
	 * @param calendar
	 * @return
	 */
	public static Date fromCalendar(Calendar calendar){
		if(calendar == null) return null;
		return calendar.getTime();
	}
}
