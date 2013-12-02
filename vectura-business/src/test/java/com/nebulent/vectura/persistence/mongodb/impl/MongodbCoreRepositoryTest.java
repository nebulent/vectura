/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.impl;


import java.util.Calendar;
import java.util.Map;

import net.sourceforge.jgeocoder.AddressComponent;
import net.sourceforge.jgeocoder.us.AddressParser;
import net.sourceforge.jgeocoder.us.AddressStandardizer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.nebulent.vectura.data.model.mongodb.Account;
import com.nebulent.vectura.data.model.mongodb.Place;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;
import com.nebulent.vectura.services.SignatureService;

/**
 * @author mfedorov
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/vectura-test.xml"})
public class MongodbCoreRepositoryTest {

	@Autowired
	CoreRepository mongoRepository;
	@Autowired
	SignatureService signatureService;
	
	@Test
    public void testSignatureService(){
		String apiKey = "Qow6ZxHcW53uND2VKy3j7ejn1mecsZGA";
		String secretKey = "YqufEj83AVaXsccbd49CQ5O2yPymlXlM";
		
		String timestamp = signatureService.generateTimestamp(Calendar.getInstance());
		System.out.println("(\"Vectura-Timestamp\",\"" + timestamp + "\")");
		System.out.println("(\"Vectura-ApiKey\",\"" + apiKey + "\")");
		try {
			System.out.println("(\"Vectura-Signature\",\"" + signatureService.generateSignature(apiKey, timestamp, secretKey) + "\")");
		} catch (Exception e) {
			Assert.isTrue(false);
		}
	}
	
	@Test
	public void testGetLocationsByDistance(){
		GeoResults<Place> results = mongoRepository.getLocationsByDistance("526a9a88472874c5685cfd1e", new double[]{-75.0555768, 40.2160837});
		for (GeoResult<Place> geoResult : results) {
			System.out.println(geoResult.getDistance().getValue() + "-->" + geoResult.getContent().getAddress().toString());
		}
	}
	
    @Test
    public void testFindAccountByApiKey(){
        Account account = mongoRepository.getAccountRepository().findByApiKey("ABC");
        Assert.notNull(account);
        System.out.println(account.getApiKey());
    }
    
    @Test
    public void testFindPlaceByIdAndAddressHash(){
        Place place = mongoRepository.getPlaceRepository().findByAccountUuidAndAddressHash("529b5b4847287c917da14c88", 1207257687);
        Assert.notNull(place);
        System.out.println(place.getAddress().toString());
    }
    
    @Test
    public void getDigest(){
		com.nebulent.vectura.data.model.mongodb.core.AddressInfo hospital2 = new com.nebulent.vectura.data.model.mongodb.core.AddressInfo();
		hospital2.setName("Holy Redeemer Hospital");
		hospital2.setAddressLine1("1648 Huntingdon Pike");
		hospital2.setCity("Jenkintown");
		hospital2.setStateOrProvince("PA");
		hospital2.setZipCode("19046");
		hospital2.setCountryCode("US");
		hospital2.setHash(hospital2.hashCode());
		
		System.out.println("Our:" + hospital2.toSingleLine());
		Map<AddressComponent, String> parsedAddr  = AddressParser.parseAddress(hospital2.toSingleLine());
		//Map<AddressComponent, String> normalizedAddr  = AddressStandardizer.normalizeParsedAddress(parsedAddr); 
	    String normalized = AddressStandardizer.toSingleLine(parsedAddr);
		System.out.println("Generated:" + normalized);
	}
}
