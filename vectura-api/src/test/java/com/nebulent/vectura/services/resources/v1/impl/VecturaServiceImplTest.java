/**
 * 
 */
package com.nebulent.vectura.services.resources.v1.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import nebulent.schema.software.vectura._1.Account;
import nebulent.schema.software.vectura._1.AddressInfo;
import nebulent.schema.software.vectura._1.ContactType;
import nebulent.schema.software.vectura._1.ContactTypeEnum;
import nebulent.schema.software.vectura._1.Location;
import nebulent.schema.software.vectura._1.NameValueType;
import nebulent.schema.software.vectura._1.Patient;
import nebulent.schema.software.vectura._1.PhoneInfo;
import nebulent.schema.software.vectura._1.PhoneTypeEnum;
import nebulent.schema.software.vectura._1.Ride;
import nebulent.schema.software.vectura._1.User;
import nebulent.schema.software.vectura._1.Vehicle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nebulent.vectura.services.resources.v1.AccountResource;
import com.nebulent.vectura.services.resources.v1.AdminResource;
import com.nebulent.vectura.services.resources.v1.ResourceTestBase;

/**
 * @author mfedorov
 *
 */
public class VecturaServiceImplTest extends ResourceTestBase {

	String v1Address = "http://localhost:9090/api";
	String v1AdminAddress = "http://localhost:9090/api/admin";
    String username = "someCrazyUzerNm";
    String password = "Theoisd89sufdkkted23";
    
    ObjectMapper jacksonJsonMapper = new ObjectMapper();
	
    protected static final Logger logger = LoggerFactory.getLogger(VecturaServiceImplTest.class);
	
	@Test
	public void testCreateAccount(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("", "");
		AdminResource accountResource = instantiateClient(v1AdminAddress, username, password, AdminResource.class, true, headers);
		
		Account account = new Account();
		account.setEin(RandomStringUtils.randomNumeric(9));
		account.setName(RandomStringUtils.randomAlphabetic(10));
		account.setApiKey(RandomStringUtils.randomAlphanumeric(32));
		account.setSecretKey(RandomStringUtils.randomAlphanumeric(32).getBytes());
		
		Vehicle vehicle = new Vehicle();
		vehicle.setColor("Blue Sapphire");
		vehicle.setMake("Chevrolet");
		vehicle.setModel("Volt");
		vehicle.setYear(2013);
		vehicle.setSeats(4);
		account.getVehicles().add(vehicle);
		
		vehicle = new Vehicle();
		vehicle.setColor("Black");
		vehicle.setMake("Land Rover");
		vehicle.setModel("Range Rover Sport");
		vehicle.setYear(2011);
		vehicle.setSeats(5);
		account.getVehicles().add(vehicle);
		
		AddressInfo address = new AddressInfo();
		address.setAddressLine1("55 Kasi Circle");
		address.setCity("Warminster");
		address.setStateOrProvince("PA");
		address.setZipCode("18974");
		// 40.224238, -75.056587
		address.setLat(new BigDecimal(40.224238));
		address.setLon(new BigDecimal(-75.056587));
		account.getAddresses().add(address);
		
		PhoneInfo phoneCell = new PhoneInfo();
		phoneCell.setType(PhoneTypeEnum.CELL.toString());
		phoneCell.setValue("267-408-3699");
		account.getPhones().add(phoneCell);
		
		PhoneInfo phoneBiz = new PhoneInfo();
		phoneBiz.setType(PhoneTypeEnum.BUSINESS.toString());
		phoneBiz.setValue("215-904-0004");
		account.getPhones().add(phoneBiz);
		
		ContactType contact = new ContactType();
		contact.setEmail("mfedorov@netflexity.com");
		contact.setFirstName("Max");
		contact.setLastName("Fedorov");
		contact.setType(ContactTypeEnum.PRIMARY.toString());
		contact.getAddresses().add(address);
		contact.getPhones().add(phoneCell);
		account.getContacts().add(contact);
		
		contact = new ContactType();
		contact.setEmail("55marinaf@gmail.com");
		contact.setFirstName("Marina");
		contact.setLastName("Fedorov");
		contact.setType(ContactTypeEnum.SECONDARY.toString());
		contact.getAddresses().add(address);
		contact.getPhones().add(phoneCell);
		account.getContacts().add(contact);
		
		User user = new User();
		user.setUsername("ageller");
		user.setPasswordHash("passw0rd".getBytes());
		user.setLastLogin(Calendar.getInstance());
		user.setEmail("ageller.biz@gmail.com");
		user.setFirstName("Arthur");
		user.setLastName("Geller");
		user.setType(ContactTypeEnum.PRIMARY.toString());
		user.getAddresses().add(address);
		user.getPhones().add(phoneCell);
		account.getUsers().add(user);
		
		Patient patient = new Patient();
		patient.setSsn("111-11-1111");
		patient.setLocations(null);
		patient.setEmail("patient1@gmail.com");
		patient.setFirstName("John");
		patient.setLastName("Deer");
		patient.setType(ContactTypeEnum.PRIMARY.toString());
		patient.getAddresses().add(address);
		patient.getPhones().add(phoneCell);
		account.getPatients().add(patient);
		
		NameValueType nv = new NameValueType();
		nv.setName("locale");
		nv.setValue("US");
		
		account.getSettings().add(nv);
		
		try {
			logger.debug("Trying to add account:" + jacksonJsonMapper.writeValueAsString(account));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		accountResource.createAccount(account);
	}
	
	@Test
	public void createRides(){
		List<Ride> rides = new ArrayList<Ride>();
		
		AddressInfo ariaHealthAddress = new AddressInfo();
		ariaHealthAddress.setAddressLine1("10800 Knights Rd");
		ariaHealthAddress.setCity("Philadelphia");
		ariaHealthAddress.setStateOrProvince("PA");
		ariaHealthAddress.setZipCode("19114");
		ariaHealthAddress.setCountryCode("US");
		
		Location ariaHealthLocation = new Location();
		ariaHealthLocation.setAddress(ariaHealthAddress);
		ariaHealthLocation.setName("Aria Health");
		
		Calendar appointmentTime = Calendar.getInstance();
		appointmentTime.set(Calendar.HOUR_OF_DAY, 8);
		String dateString = DateFormatUtils.format(appointmentTime, "MM/dd/yyyy");
		
		List<Location> locations = getLocations();
		for (Location loc : locations) {
			Ride ride = new Ride();
			ride.setAdditionalRiders(0);
			ride.setDateAsString(dateString);
			ride.setAppointmentOn(appointmentTime);
			ride.setExtTripId(UUID.randomUUID().toString());
			ride.setMileage(1.0);
			ride.setPickupAddress(loc.getAddress());
			ride.setDropOffAddress(ariaHealthAddress);
		}
		
		AccountResource accountResource = instantiateClient(v1Address, username, password, AccountResource.class, true, getHeaders());
		for (Ride ride : rides) {
			ride = accountResource.createAccountRide("526a9a88472874c5685cfd1e", ride);
			if(ride != null){
				try {
					logger.debug("Location:" + jacksonJsonMapper.writeValueAsString(ride));
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void createLocations(){
		List<Location> locations = getLocations();
		
		AccountResource accountResource = instantiateClient(v1Address, username, password, AccountResource.class, true, getHeaders());
		for (Location loc : locations) {
			loc = accountResource.createAccountLocation("526a9a88472874c5685cfd1e", loc);
			if(loc != null){
				try {
					logger.debug("Location:" + jacksonJsonMapper.writeValueAsString(loc));
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testFindAccountById(){
		AccountResource accountResource = instantiateClient(v1Address, username, password, AccountResource.class, true, getHeaders());
		Account account = accountResource.findAccount("526a9a88472874c5685cfd1e");//headers.get("Vectura-ApiKey"));
		if(account != null){
			try {
				logger.debug("Go email account:" + jacksonJsonMapper.writeValueAsString(account));
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Fail on purpose.
		Map<String, String> headers = getHeaders();
		headers.put("Vectura-Signature","ZZZFAILBpclYWPE52I/q04vbeSyregOhw=");
		
		accountResource = instantiateClient(v1Address, username, password, AccountResource.class, true, headers);
		try {
			account = accountResource.findAccount("526a9a88472874c5685cfd1e");//headers.get("Vectura-ApiKey"));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
	}
	
	/**
	 * @return
	 */
	protected List<Location> getLocations(){
		List<Location> locations = new ArrayList<Location>();
		
		AddressInfo address = new AddressInfo();
		address.setAddressLine1("114 South 18th Street");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19103");
		address.setCountryCode("US");
		
		Location location = new Location();
		location.setAddress(address);
		location.setName("Byblos");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("490 Woodhaven Rd.");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19116");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Alex Fedorov");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("157 Forge Lane");
		address.setCity("Feasterville");
		address.setStateOrProvince("PA");
		address.setZipCode("19053");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Ilya Pekarev");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("648 Korisa Drive");
		address.setCity("Huntingdon Valley");
		address.setStateOrProvince("PA");
		address.setZipCode("19006");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Mark Kogan");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("1630 Welsh Rd Unit G56");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19115");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Marina Khanina");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("55 Kasi Circle");
		address.setCity("Warminster");
		address.setStateOrProvince("PA");
		address.setZipCode("18974");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Max Fedorov");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("51 Hope Rd.");
		address.setCity("Holland");
		address.setStateOrProvince("PA");
		address.setZipCode("18966");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Alex Zhitomirsky");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("755 Edden Ct.");
		address.setCity("Southampton");
		address.setStateOrProvince("PA");
		address.setZipCode("18966");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Mark Jacobs");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("2368 Dorchester St. West");
		address.setCity("Furlong");
		address.setStateOrProvince("PA");
		address.setZipCode("18925");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Max Fedorov II");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("650 Street Rd.");
		address.setCity("Feasterville");
		address.setStateOrProvince("PA");
		address.setZipCode("19053");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Sol Luna Tea Room");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("1040 Mill Creek Dr.");
		address.setCity("Feasterville");
		address.setStateOrProvince("PA");
		address.setZipCode("19053");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("B&R Family Fitness");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("220 Sycamore Cir.");
		address.setCity("Feasterville");
		address.setStateOrProvince("PA");
		address.setZipCode("19053");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Adam Smith");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("585 S Mt Vernon Circle");
		address.setCity("Bensalem");
		address.setStateOrProvince("PA");
		address.setZipCode("19020");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("John Adams");
		locations.add(location);
		
		address = new AddressInfo();
		address.setAddressLine1("1172 Bartlett Pl.");
		address.setCity("Phila");
		address.setStateOrProvince("PA");
		address.setZipCode("19115");
		address.setCountryCode("US");
		
		location = new Location();
		location.setAddress(address);
		location.setName("Steve Bartlett");
		locations.add(location);
		
		return locations;
	}
	
	/**
	 * @return
	 */
	protected Location getOfficeLocation(){
		AddressInfo address = new AddressInfo();
		address.setAddressLine1("1714 Grant Ave");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19115");
		address.setCountryCode("US");
		
		Location location = new Location();
		location.setAddress(address);
		location.setName("AllCityRental");
		
		return location;
	}
	
	/**
     * @return
     */
    protected Map<String, String> getHeaders(){
	    Map<String, String> headers = new HashMap<String, String>();
	    headers.put("Vectura-Timestamp","2013-10-25T16:36:53.819Z");
		headers.put("Vectura-ApiKey","Qow6ZxHcW53uND2VKy3j7ejn1mecsZGA");
		headers.put("Vectura-Signature","yBpclYWPE52I/q04vbeSyregOhw=");
		return headers;
    }
}
