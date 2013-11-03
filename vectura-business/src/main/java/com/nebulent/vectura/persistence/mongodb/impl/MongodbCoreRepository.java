/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import com.nebulent.vectura.data.model.mongodb.Location;
import com.nebulent.vectura.persistence.mongodb.BaseMongodbRepository;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;
import com.nebulent.vectura.persistence.repositories.mongodb.AccountRepository;
import com.nebulent.vectura.persistence.repositories.mongodb.LocationRepository;
import com.nebulent.vectura.persistence.repositories.mongodb.RideRepository;
import com.nebulent.vectura.persistence.repositories.mongodb.RunRepository;

/**
 * @author Max Fedorov
 *
 */
public class MongodbCoreRepository extends BaseMongodbRepository implements CoreRepository{
	
	public static final String FIELD_ACCOUNT_UUID = "accountUuid";

	public static final String FIELD_ID = "_id";
	
	protected static final Logger logger = LoggerFactory.getLogger(MongodbCoreRepository.class);
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private RideRepository rideRepository;
	@Autowired
	private RunRepository runRepository;
	
	/* (non-Javadoc)
	 * @see com.redrover.persistence.repository.mongodb.BaseMongodbRepository#init()
	 */
	@Override
	protected void init() {
		super.init();
	}

	/**
	 * @return the accountRepository
	 */
	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	/**
	 * @param accountRepository the accountRepository to set
	 */
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	/**
	 * @return the locationRepository
	 */
	public LocationRepository getLocationRepository() {
		return locationRepository;
	}

	/**
	 * @param locationRepository the locationRepository to set
	 */
	public void setLocationRepository(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	/**
	 * @return the rideRepository
	 */
	public RideRepository getRideRepository() {
		return rideRepository;
	}

	/**
	 * @param rideRepository the rideRepository to set
	 */
	public void setRideRepository(RideRepository rideRepository) {
		this.rideRepository = rideRepository;
	}

	/**
	 * @return the runRepository
	 */
	public RunRepository getRunRepository() {
		return runRepository;
	}

	/**
	 * @param runRepository the runRepository to set
	 */
	public void setRunRepository(RunRepository runRepository) {
		this.runRepository = runRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.nebulent.vectura.persistence.mongodb.CoreRepository#getLocationsByDistance(java.lang.String, double[])
	 */
	@Override
	public GeoResults<Location> getLocationsByDistance(String accountUuid, double[] position){
		Point location = new Point(position[0], position[1]);
		Query query = Query.query(Criteria.where(FIELD_ACCOUNT_UUID).is(accountUuid));
		NearQuery nearQuery = NearQuery.near(location).maxDistance(new Distance(100, Metrics.MILES)).query(query);
		GeoResults<Location> results = mongoTemplate.geoNear(nearQuery, Location.class);
		return results;
	}
}
