/**
 * 
 */
package com.nebulent.vectura.persistence.repositories.mongodb;

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
	 * @param date
	 * @return
	 */
	public List<Ride> findByAccountUuidDateOrderByApptOnAsc(String accountUuid, String date);
	
}
