/**
 * 
 */
package com.nebulent.vectura.persistence.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nebulent.vectura.data.model.mongodb.Run;

/**
 * @author mfedorov
 *
 */
public interface RunRepository extends MongoRepository<Run, String>{

}
