/**
 * 
 */
package com.nebulent.vectura.persistence.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Account;

/**
 * @author mfedorov
 *
 */
public interface AccountRepository extends MongoRepository<Account, String>{

	/**
	 * @param apiKey
	 * @return
	 */
	public Account findByApiKey(String apiKey);
}
