/**
 * 
 */
package com.nebulent.vectura.persistence.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Location;

/**
 * @author mfedorov
 *
 */
public interface LocationRepository extends MongoRepository<Location, String>{

}
