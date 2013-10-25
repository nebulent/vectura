/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.nebulent.vectura.data.model.mongodb.Account;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;

/**
 * @author mfedorov
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/vectura-test.xml"})
public class MongodbCoreRepositoryTest {

	@Autowired
	CoreRepository mongoRepository;

    @Test
    public void testFindAccountByApiKey(){
        Account account = mongoRepository.getAccountRepository().findByApiKey("ABC");
        Assert.notNull(account);
        System.out.println(account.getApiKey());
    }
}