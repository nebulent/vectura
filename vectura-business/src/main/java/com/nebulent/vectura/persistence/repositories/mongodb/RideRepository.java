/**
 * 
 */
package com.nebulent.vectura.persistence.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Ride;

/**
 * @author mfedorov
 *
 */
public interface RideRepository extends MongoRepository<Ride, String>{

}
