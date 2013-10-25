/**
 * 
 */
package com.nebulent.vectura.services.resources.v1.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import nebulent.schema.software.vectura._1.Account;
import nebulent.schema.software.vectura._1.AddressInfo;
import nebulent.schema.software.vectura._1.ContactType;
import nebulent.schema.software.vectura._1.ContactTypeEnum;
import nebulent.schema.software.vectura._1.Location;
import nebulent.schema.software.vectura._1.NameValueType;
import nebulent.schema.software.vectura._1.Patient;
import nebulent.schema.software.vectura._1.PhoneInfo;
import nebulent.schema.software.vectura._1.PhoneTypeEnum;
import nebulent.schema.software.vectura._1.User;
import nebulent.schema.software.vectura._1.Vehicle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
		phoneCell.setType(PhoneTypeEnum.CELL.name());
		phoneCell.setValue("267-408-3699");
		account.getPhones().add(phoneCell);
		
		PhoneInfo phoneBiz = new PhoneInfo();
		phoneBiz.setType(PhoneTypeEnum.BUSINESS.name());
		phoneBiz.setValue("215-904-0004");
		account.getPhones().add(phoneBiz);
		
		ContactType contact = new ContactType();
		contact.setEmail("mfedorov@netflexity.com");
		contact.setFirstName("Max");
		contact.setLastName("Fedorov");
		contact.setType(ContactTypeEnum.PRIMARY.name());
		contact.getAddresses().add(address);
		contact.getPhones().add(phoneCell);
		account.getContacts().add(contact);
		
		contact = new ContactType();
		contact.setEmail("55marinaf@gmail.com");
		contact.setFirstName("Marina");
		contact.setLastName("Fedorov");
		contact.setType(ContactTypeEnum.SECONDARY.name());
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
		user.setType(ContactTypeEnum.PRIMARY.name());
		user.getAddresses().add(address);
		user.getPhones().add(phoneCell);
		account.getUsers().add(user);
		
		Patient patient = new Patient();
		patient.setSsn("111-11-1111");
		patient.setLocations(null);
		patient.setEmail("patient1@gmail.com");
		patient.setFirstName("John");
		patient.setLastName("Deer");
		patient.setType(ContactTypeEnum.PRIMARY.name());
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
	
	public void createLocations(){
		AddressInfo address = new AddressInfo();
		address.setAddressLine1("55 Kasi Circle");
		address.setCity("Warminster");
		address.setStateOrProvince("PA");
		address.setZipCode("18974");
		// 40.224238, -75.056587
		address.setLat(new BigDecimal(40.224238));
		address.setLon(new BigDecimal(-75.056587));
		
		StringBuilder locationNameBuilder = new StringBuilder(256);
		locationNameBuilder.append(address.getAddressLine1().toUpperCase().trim());
		if(StringUtils.isNotBlank(address.getAddressLine2())){
			locationNameBuilder.append(" ").append(address.getAddressLine2().toUpperCase().trim());
		}
		if(StringUtils.isNotBlank(address.getAddressLine3())){
			locationNameBuilder.append(" ").append(address.getAddressLine3().toUpperCase().trim());
		}
		locationNameBuilder.append(" ").append(address.getCity().toUpperCase().trim())
		.append(" ").append(address.getStateOrProvince().toUpperCase().trim())
		.append(" ").append(address.getZipCode().toUpperCase().trim())
		.append(" ").append(address.getCountryCode().toUpperCase().trim());
		
		Location location = new Location();
		location.setAddress(address);
		location.setName(locationNameBuilder.toString());
	}
	
	@Test
	public void testFindAccountById(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("", "");
		
		AccountResource accountResource = instantiateClient(v1Address, username, password, AccountResource.class, true, headers);
		Account account = accountResource.findAccount("mfedorov@itemize.com");
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
	}
	
}
