/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.impl;


import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.nebulent.vectura.data.model.mongodb.Account;
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
    public void testFindAccountByApiKey(){
        Account account = mongoRepository.getAccountRepository().findByApiKey("ABC");
        Assert.notNull(account);
        System.out.println(account.getApiKey());
    }
}
