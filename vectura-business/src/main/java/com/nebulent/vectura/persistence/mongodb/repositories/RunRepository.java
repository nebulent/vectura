/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Run;

/**
 * @author mfedorov
 *
 */
public interface RunRepository extends MongoRepository<Run, String>{

}
