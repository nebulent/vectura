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
import java.util.Random;
import java.util.UUID;

import nebulent.schema.software.vectura._1.Account;
import nebulent.schema.software.vectura._1.AddressInfo;
import nebulent.schema.software.vectura._1.ContactType;
import nebulent.schema.software.vectura._1.ContactTypeEnum;
import nebulent.schema.software.vectura._1.NameValueType;
import nebulent.schema.software.vectura._1.Patient;
import nebulent.schema.software.vectura._1.PhoneInfo;
import nebulent.schema.software.vectura._1.PhoneTypeEnum;
import nebulent.schema.software.vectura._1.Place;
import nebulent.schema.software.vectura._1.Ride;
import nebulent.schema.software.vectura._1.User;
import nebulent.schema.software.vectura._1.UserTypeEnum;
import nebulent.schema.software.vectura._1.Vehicle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.nebulent.vectura.services.resources.v1.AccountResource;
import com.nebulent.vectura.services.resources.v1.AdminResource;

/**
 * @author mfedorov
 *
 */
public class VecturaServiceImplTest{

	String v1Address = "http://localhost:8080/api/v1";
	String v1AdminAddress = "http://localhost:8080/api/v1/admin";
    String username = "someCrazyUzerNm";
    String password = "Theoisd89sufdkkted23";
    
    ObjectMapper jacksonJsonMapper = new ObjectMapper();
    final Random random = new Random();
	final int MILLIS_IN_A_DAY = 24*60*60*1000;
	final int MILLIS_IN_A_DAY_8AM = 16*60*60*1000;
	
    protected static final Logger logger = LoggerFactory.getLogger(VecturaServiceImplTest.class);
	
	@Test
	public void testSetupAccountAndData(){
		AdminResource accountResource = basicAuthClient(v1AdminAddress, username, password, AdminResource.class);
		
		Account account = new Account();
		account.setEin(RandomStringUtils.randomNumeric(9));
		account.setName(RandomStringUtils.randomAlphabetic(10));
		account.setApiKey("Qow6ZxHcW53uND2VKy3j7ejn1mecsZGA");
		account.setSecretKey("YqufEj83AVaXsccbd49CQ5O2yPymlXlM".getBytes());
		
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
		user.setType(UserTypeEnum.ADMIN.toString());
		user.getAddresses().add(address);
		user.getPhones().add(phoneCell);
		account.getUsers().add(user);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setVin(UUID.randomUUID().toString());
		vehicle.setColor("Blue Sapphire");
		vehicle.setMake("Chevrolet");
		vehicle.setModel("Volt");
		vehicle.setYear(2013);
		vehicle.setSeats(4);
		account.getVehicles().add(vehicle);
		
		User driver = new User();
		driver.setUsername("driver1");
		driver.setPasswordHash("passw0rd".getBytes());
		driver.setLastLogin(Calendar.getInstance());
		driver.setEmail("arthur@allcityrental.com");
		driver.setFirstName("Mr");
		driver.setLastName("Driver1");
		driver.setType(UserTypeEnum.DRIVER.toString());
		driver.setVin(vehicle.getVin());
		driver.getAddresses().add(address);
		driver.getPhones().add(phoneCell);
		account.getUsers().add(driver);
		
		vehicle = new Vehicle();
		vehicle.setVin(UUID.randomUUID().toString());
		vehicle.setColor("Black");
		vehicle.setMake("Land Rover");
		vehicle.setModel("Range Rover Sport");
		vehicle.setYear(2011);
		vehicle.setSeats(5);
		account.getVehicles().add(vehicle);
		
		driver = new User();
		driver.setUsername("driver2");
		driver.setPasswordHash("passw0rd".getBytes());
		driver.setLastLogin(Calendar.getInstance());
		driver.setEmail("max@allcityrental.com");
		driver.setFirstName("Mr");
		driver.setLastName("Driver2");
		driver.setType(UserTypeEnum.DRIVER.toString());
		driver.setVin(vehicle.getVin());
		driver.getAddresses().add(address);
		driver.getPhones().add(phoneCell);
		account.getUsers().add(driver);
		
		Patient patient = new Patient();
		patient.setSsn("111-11-1111");
		//patient.setPlace(value);
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
		
		Account createAccount = accountResource.createAccount(account);
		
		List<Place> places = getPlaces();
		
		//places = createPlaces(createAccount.getId(), places);
		
		createRides(createAccount.getId(), places);
	}
	
	/**
	 * @param accountId
	 */
	public void createRides(String accountId, List<Place> places){
		List<Ride> rides = new ArrayList<Ride>();
		
		AddressInfo hospital1 = new AddressInfo();
		hospital1.setName("Aria Health");
		hospital1.setAddressLine1("10800 Knights Rd");
		hospital1.setCity("Philadelphia");
		hospital1.setStateOrProvince("PA");
		hospital1.setZipCode("19114");
		hospital1.setCountryCode("US");
		
		Place hospitalPlace1 = new Place();
		hospitalPlace1.setAddress(hospital1);
		hospitalPlace1.setName("Aria Health");
		
		AddressInfo hospital2 = new AddressInfo();
		hospital2.setName("Holy Redeemer Hospital");
		hospital2.setAddressLine1("1648 Huntingdon Pike");
		hospital2.setCity("Jenkintown");
		hospital2.setStateOrProvince("PA");
		hospital2.setZipCode("19046");
		hospital2.setCountryCode("US");
		
		Place hospitalPlace2 = new Place();
		hospitalPlace2.setAddress(hospital2);
		hospitalPlace2.setName("Holy Redeemer Hospital");
		
		List<Place> hospitals = new ArrayList<Place>();
		hospitals.add(hospitalPlace1);
		hospitals.add(hospitalPlace2);
		
		//createPlaces(accountId, hospitals); //Arrays.asList(new Place[] {hospitalPlace1,hospitalPlace2}));
		
		Calendar appointmentTime = Calendar.getInstance();
		appointmentTime.set(Calendar.DAY_OF_MONTH, appointmentTime.get(Calendar.DAY_OF_MONTH) + 1);
		appointmentTime.set(Calendar.HOUR_OF_DAY, 8);
		String dateString = DateFormatUtils.format(appointmentTime, "MM/dd/yyyy");
		
		for (Place loc : places) {
			int hospitalRandom = random.nextInt(2);
			
			Ride ride = new Ride();
			rides.add(ride);
			ride.setPrice(1.00);
			ride.setAdditionalRiders(0);
			ride.setDateAsString(dateString);
			ride.setAppointmentOn(getRandomAppointmentTime(appointmentTime));
			ride.setExtTripId(UUID.randomUUID().toString());
			ride.setMileage(1.0);
			ride.setPickupAddress(loc.getAddress());
			if(hospitalRandom == 0){
				ride.setDropOffAddress(hospital1);
			}
			else{
				ride.setDropOffAddress(hospital2);
			}
			System.out.println(ride.getAppointmentOn().getTime() + "----" + ride.getPickupAddress().getAddressLine1() + "=>" + ride.getDropOffAddress().getAddressLine1());
		}
		
		AccountResource accountResource = instantiateClient(v1Address, AccountResource.class, getHeaders());
		for (Ride ride : rides) {
			ride = accountResource.createAccountRide(accountId, ride);
			if(ride != null){
				try {
					logger.debug("Place:" + jacksonJsonMapper.writeValueAsString(ride));
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
	
	/**
	 * @param accountId
	 * @param locations
	 */
	protected List<Place> createPlaces(String accountId, List<Place> locations){
		List<Place> places = new ArrayList<Place>(locations.size());
		
		AccountResource accountResource = instantiateClient(v1Address, AccountResource.class, getHeaders());
		for (Place loc : locations) {
			loc = accountResource.createAccountPlace(accountId, loc);
			if(loc != null){
				places.add(loc);
				try {
					logger.debug("Place:" + jacksonJsonMapper.writeValueAsString(loc));
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
		return places;
	}
	
	@Test
	public void testFindAccountById(){
		AccountResource accountResource = instantiateClient(v1Address, AccountResource.class, getHeaders());
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
		
		accountResource = instantiateClient(v1Address, AccountResource.class, headers);
		try {
			account = accountResource.findAccount("526a9a88472874c5685cfd1e");//headers.get("Vectura-ApiKey"));
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
	}
	
	/**
	 * @return
	 */
	protected List<Place> getPlaces(){
		List<Place> places = new ArrayList<Place>();
		
		AddressInfo address = new AddressInfo();
		address.setAddressLine1("114 South 18th Street");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19103");
		address.setCountryCode("US");
		
		Place place = new Place();
		place.setAddress(address);
		place.setName("Byblos");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("490 Woodhaven Rd.");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19116");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Alex Fedorov");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("157 Forge Lane");
		address.setCity("Feasterville");
		address.setStateOrProvince("PA");
		address.setZipCode("19053");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Ilya Pekarev");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("648 Korisa Drive");
		address.setCity("Huntingdon Valley");
		address.setStateOrProvince("PA");
		address.setZipCode("19006");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Mark Kogan");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("1630 Welsh Rd Unit G56");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19115");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Marina Khanina");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("55 Kasi Circle");
		address.setCity("Warminster");
		address.setStateOrProvince("PA");
		address.setZipCode("18974");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Max Fedorov");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("51 Hope Rd.");
		address.setCity("Holland");
		address.setStateOrProvince("PA");
		address.setZipCode("18966");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Alex Zhitomirsky");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("755 Edden Ct.");
		address.setCity("Southampton");
		address.setStateOrProvince("PA");
		address.setZipCode("18966");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Mark Jacobs");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("2368 Dorchester St. West");
		address.setCity("Furlong");
		address.setStateOrProvince("PA");
		address.setZipCode("18925");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Max Fedorov II");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("650 Street Rd.");
		address.setCity("Feasterville");
		address.setStateOrProvince("PA");
		address.setZipCode("19053");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Sol Luna Tea Room");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("2020 County Line Rd.");
		address.setCity("Huntingdon Valley");
		address.setStateOrProvince("PA");
		address.setZipCode("19006");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("LA Fitness");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("220 Sycamore Cir.");
		address.setCity("Feasterville");
		address.setStateOrProvince("PA");
		address.setZipCode("19053");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Adam Smith");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("585 S Mt Vernon Circle");
		address.setCity("Bensalem");
		address.setStateOrProvince("PA");
		address.setZipCode("19020");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("John Adams");
		places.add(place);
		
		address = new AddressInfo();
		address.setAddressLine1("1172 Bartlett Pl.");
		address.setCity("Phila");
		address.setStateOrProvince("PA");
		address.setZipCode("19115");
		address.setCountryCode("US");
		
		place = new Place();
		place.setAddress(address);
		place.setName("Steve Bartlett");
		places.add(place);
		
		return places;
	}
	
	/**
	 * @return
	 */
	protected Place getOfficePlace(){
		AddressInfo address = new AddressInfo();
		address.setAddressLine1("1714 Grant Ave");
		address.setCity("Philadelphia");
		address.setStateOrProvince("PA");
		address.setZipCode("19115");
		address.setCountryCode("US");
		
		Place location = new Place();
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
    
    /**
	 * @param address
	 * @param username
	 * @param password
	 * @param resource
	 * @return
	 */
	protected <T> T basicAuthClient(String address, String username, String password, Class<T> resource) {
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();       
        bean.setAddress(address);
        bean.setServiceClass(resource);
        bean.setUsername(username);
        bean.setPassword(password);
        
        //Adding JSON converter
		JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
		//jacksonJsonProvider.enable(JsonParser.Feature.ALLOW_COMMENTS, true);
        bean.setProvider(jacksonJsonProvider);
        
        //Adding logging(not necessary)
        List<AbstractFeature> features = new ArrayList<AbstractFeature>();
        features.add(new LoggingFeature());
        bean.setFeatures(features);
        
        //Getting user list
        return bean.create(resource);
	}
	
	/**
	 * @param address
	 * @param resource
	 * @param headers
	 * @return
	 */
	protected <T> T instantiateClient(String address, Class<T> resource, Map<String, String> headers) {
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();       
        bean.setAddress(address);
        bean.setServiceClass(resource);
        if(headers != null && !headers.isEmpty()){
        	bean.setHeaders(headers);
        }
        
        //Adding JSON converter
		JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
		jacksonJsonProvider.enable(JsonParser.Feature.ALLOW_COMMENTS, true);
        bean.setProvider(jacksonJsonProvider);
        
        //Adding logging(not necessary)
        List<AbstractFeature> features = new ArrayList<AbstractFeature>();
        features.add(new LoggingFeature());
        bean.setFeatures(features);
        
        //Getting user list
        return bean.create(resource);
	}
	
	/**
	 * @param appointmentTimeSeed
	 * @return
	 */
	private Calendar getRandomAppointmentTime(Calendar appointmentTimeSeed){
		int hour = appointmentTimeSeed.get(Calendar.HOUR_OF_DAY);
		int min = appointmentTimeSeed.get(Calendar.MINUTE);
		int sec = appointmentTimeSeed.get(Calendar.SECOND);
		int ms = appointmentTimeSeed.get(Calendar.MILLISECOND);
		int timeNow = (hour*min*sec*1000) - (1000-ms);
		long millis = random.nextInt(MILLIS_IN_A_DAY - MILLIS_IN_A_DAY_8AM - (timeNow - MILLIS_IN_A_DAY_8AM));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(appointmentTimeSeed.getTimeInMillis() + millis);
		return cal;
	}
}
