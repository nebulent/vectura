/**
 * 
 */
package com.nebulent.vectura.persistence.repositories.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Location;

/**
 * @author mfedorov
 *
 */
public interface LocationRepository extends MongoRepository<Location, String>{
	
	/**
	 * @param accountUuid
	 * @param hash
	 * @return
	 */
	public List<Location> findByAccountUuidAddressHash(String accountUuid, String hash);
}
