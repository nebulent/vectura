/**
 * 
 */
package com.nebulent.vectura.services.resources.v1.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nebulent.schema.software.vectura._1.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebulent.vectura.persistence.mongodb.CoreRepository;
import com.nebulent.vectura.services.resources.v1.AdminResource;
import com.nebulent.vectura.services.utils.DomainUtils;

/**
 * @author mfedorov
 *
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/")
public class VecturaAdminServiceImpl implements AdminResource {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CoreRepository mongoRepository;

	/**
	 * @return the mongoRepository
	 */
	public CoreRepository getMongoRepository() {
		return mongoRepository;
	}

	/**
	 * @param mongoRepository the mongoRepository to set
	 */
	public void setMongoRepository(CoreRepository mongoRepository) {
		this.mongoRepository = mongoRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AdminResource#updateAccount(java.lang.String, nebulent.schema.software.vectura._1.Account)
	 */
	@Override
	public Account updateAccount(String accountId, Account account) {
		getMongoRepository().getAccountRepository().save(DomainUtils.toAccount(account));
		return account;
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AdminResource#createAccount(nebulent.schema.software.vectura._1.Account)
	 */
	@Override
	public Account createAccount(Account account) {
		com.nebulent.vectura.data.model.mongodb.Account savedAccount = getMongoRepository().getAccountRepository().save(DomainUtils.toAccount(account));
		return DomainUtils.toAccount(savedAccount);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AdminResource#removeAccount(java.lang.String)
	 */
	@Override
	public Account removeAccount(String accountId) {
		getMongoRepository().getAccountRepository().delete(accountId);
		return null;
	}

}
