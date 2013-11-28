/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Ride;

/**
 * @author mfedorov
 *
 */
public interface RideRepository extends MongoRepository<Ride, String>{

	/**
	 * @param accountUuid
	 * @param rideDateAsString
	 * @return
	 */
	public List<Ride> findByAccountUuidAndRideDateAsStringOrderByApptOnAsc(String accountUuid, String rideDateAsString);
	
}
