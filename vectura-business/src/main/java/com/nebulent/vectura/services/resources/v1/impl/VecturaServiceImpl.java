/**
 * 
 */
package com.nebulent.vectura.services.resources.v1.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nebulent.schema.software.vectura._1.Account;
import nebulent.schema.software.vectura._1.AddressInfo;
import nebulent.schema.software.vectura._1.ContactType;
import nebulent.schema.software.vectura._1.Location;
import nebulent.schema.software.vectura._1.Patient;
import nebulent.schema.software.vectura._1.PhoneInfo;
import nebulent.schema.software.vectura._1.Run;
import nebulent.schema.software.vectura._1.User;
import nebulent.schema.software.vectura._1.Vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebulent.vectura.persistence.mongodb.CoreRepository;
import com.nebulent.vectura.services.MapService;
import com.nebulent.vectura.services.resources.v1.AccountResource;
import com.nebulent.vectura.services.resources.v1.LocationResource;
import com.nebulent.vectura.services.resources.v1.RunResource;
import com.nebulent.vectura.services.utils.DomainUtils;

/**
 * @author mfedorov
 *
 */
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/")
public class VecturaServiceImpl implements AccountResource, RunResource, LocationResource{

	public static final int DEFAULT_SEARCH_PAGE_SIZE_100 = 100;

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CoreRepository mongoRepository;
	@Autowired
	private MapService mapService;
	
	/**
	 * @return the mongoRepository
	 */
	public CoreRepository getMongoRepository() {
		return mongoRepository;
	}

	/**
	 * @param mongoRepository the mongoRepository to set
	 */
	public void setMongoRepository(CoreRepository mongoRepository) {
		this.mongoRepository = mongoRepository;
	}

	/**
	 * @return the mapService
	 */
	public MapService getMapService() {
		return mapService;
	}

	/**
	 * @param mapService the mapService to set
	 */
	public void setMapService(MapService mapService) {
		this.mapService = mapService;
	}

	@Override
	public Location findLocation(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Run> findRunsByLocation(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Run findRun(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccount(String accountId) {
		com.nebulent.vectura.data.model.mongodb.Account account = getMongoRepository().getAccountRepository().findOne(accountId);
		return DomainUtils.toAccount(account);
	}

	@Override
	public AddressInfo addAccountAddress(String accountId, AddressInfo address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactType addAccountContact(String accountId, ContactType contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhoneInfo addAccountPhone(String accountId, PhoneInfo phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle addAccountVehicle(String accountId, Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addAccountUser(String accountId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient addAccountPatient(String accountId, Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location createLocation(String accountId, Location locationType) {
		locationType.setAccountId(accountId);
		com.nebulent.vectura.data.model.mongodb.Location location = DomainUtils.toLocation(locationType);
		AddressInfo addressType = mapService.getLocationByAddress(location.getAddress().toString());
		com.nebulent.vectura.data.model.mongodb.AddressInfo addressInfo = DomainUtils.toAddress(addressType);
		if(addressInfo != null){
			location.setAddress(addressInfo);
		}
		location.getAddress().hash();
		location = getMongoRepository().getLocationRepository().save(location);
		return DomainUtils.toLocation(location);
	}

	@Override
	public List<Location> searchAccountLocations(String accountId, String addressHash) {
		// TODO Auto-generated method stub
		return null;
	}
}
