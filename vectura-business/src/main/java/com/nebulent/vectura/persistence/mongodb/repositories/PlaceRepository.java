/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Place;

/**
 * @author mfedorov
 *
 */
public interface PlaceRepository extends MongoRepository<Place, String>{
	
	/**
	 * @param accountUuid
	 * @param addressHash
	 * @return
	 */
	public Place findByAccountUuidAndAddressHash(String accountUuid, String addressHash);
	
	/**
	 * @param accountUuid
	 * @return
	 */
	public List<Place> findByAccountUuid(String accountUuid);
}
