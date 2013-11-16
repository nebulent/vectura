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
import nebulent.schema.software.vectura._1.Ride;
import nebulent.schema.software.vectura._1.Run;
import nebulent.schema.software.vectura._1.User;
import nebulent.schema.software.vectura._1.Vehicle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebulent.vectura.data.model.mongodb.Contact;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;
import com.nebulent.vectura.services.MapService;
import com.nebulent.vectura.services.resources.BadRequestException;
import com.nebulent.vectura.services.resources.NotFoundException;
import com.nebulent.vectura.services.resources.StatusResponse;
import com.nebulent.vectura.services.resources.v1.AccountResource;
import com.nebulent.vectura.services.resources.v1.LocationResource;
import com.nebulent.vectura.services.resources.v1.RideResource;
import com.nebulent.vectura.services.resources.v1.RunResource;
import com.nebulent.vectura.services.utils.DomainUtils;

/**
 * @author mfedorov
 *
 */
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/")
public class VecturaServiceImpl implements AccountResource, RunResource, LocationResource, RideResource{

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
		if(StringUtils.isBlank(id)){
			throw new BadRequestException(new StatusResponse(false, "Location ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.Location location = getMongoRepository().getLocationRepository().findOne(id);
		if(location != null){
			return DomainUtils.toLocation(location);
		}
		throw new NotFoundException(new StatusResponse(false, "Location not found by id:", id));
	}

	@Override
	public List<Run> findRunsByLocation(String id) {
		if(StringUtils.isBlank(id)){
			throw new BadRequestException(new StatusResponse(false, "Location ID is a required field.", null));
		}
		
		return null;
	}

	@Override
	public Run findRun(String id) {
		if(StringUtils.isBlank(id)){
			throw new BadRequestException(new StatusResponse(false, "Run ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.Run run = getMongoRepository().getRunRepository().findOne(id);
		if(run != null){
			return DomainUtils.toRun(run);
		}
		throw new NotFoundException(new StatusResponse(false, "Run not found by id:", id));
	}

	@Override
	public Account findAccount(String accountId) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.Account account = getMongoRepository().getAccountRepository().findOne(accountId);
		if(account != null){
			return DomainUtils.toAccount(account);
		}
		throw new NotFoundException(new StatusResponse(false, "Account not found by id:", accountId));
	}

	@Override
	public AddressInfo addAccountAddress(String accountId, AddressInfo address) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.AddressInfo dbAddress = DomainUtils.toAddress(address);
		getMongoRepository().addAccountAddress(accountId, dbAddress);
		return DomainUtils.toAddress(dbAddress);
	}

	@Override
	public ContactType addAccountContact(String accountId, ContactType contact) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		Contact dbContact = DomainUtils.toContact(contact);
		getMongoRepository().addAccountContact(accountId, dbContact);
		return DomainUtils.toContact(dbContact);
	}

	@Override
	public PhoneInfo addAccountPhone(String accountId, PhoneInfo phone) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.PhoneInfo dbPhone = DomainUtils.toPhone(phone);
		getMongoRepository().addAccountPhone(accountId, dbPhone);
		return DomainUtils.toPhone(dbPhone);
	}

	@Override
	public Vehicle addAccountVehicle(String accountId, Vehicle vehicle) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.Vehicle dbVehicle = DomainUtils.toVehicle(vehicle);
		getMongoRepository().addAccountVehicle(accountId, dbVehicle);
		return DomainUtils.toVehicleType(dbVehicle);
	}

	@Override
	public User addAccountUser(String accountId, User user) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.User dbUser = DomainUtils.toUser(user);
		getMongoRepository().addAccountUser(accountId, dbUser);
		return DomainUtils.toUser(dbUser);
	}

	@Override
	public Patient addAccountPatient(String accountId, Patient patient) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.Patient dbPatient = DomainUtils.toPatient(patient);
		getMongoRepository().addAccountPatient(accountId, dbPatient);
		return DomainUtils.toPatient(dbPatient);
	}

	@Override
	public Location createAccountLocation(String accountId, Location locationType) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		locationType.setAccountId(accountId);
		com.nebulent.vectura.data.model.mongodb.Location location = DomainUtils.toLocation(locationType);
		location.setAccountUuid(accountId);
		
		AddressInfo addressType = mapService.getLocationByAddress(location.getAddress().toString());
		com.nebulent.vectura.data.model.mongodb.AddressInfo addressInfo = DomainUtils.toAddress(addressType);
		if(logger.isDebugEnabled()){
			logger.debug("Adding location with address:" + addressInfo);
		}
		if(addressInfo != null && StringUtils.isNotBlank(addressInfo.getAddressLine1())){
			location.setAddress(addressInfo);
			location.getAddress().hash();
			location.setLocation(location.getAddress().getLocation());
			location = getMongoRepository().getLocationRepository().save(location);
		}
		
		return DomainUtils.toLocation(location);
	}

	@Override
	public List<Location> searchAccountLocations(String accountId, String addressHash) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		List<com.nebulent.vectura.data.model.mongodb.Location> locations = getMongoRepository().getLocationRepository().findByAccountUuidAddressHash(accountId, addressHash);
		return DomainUtils.toLocations(locations);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AccountResource#createAccountRide(java.lang.String, nebulent.schema.software.vectura._1.Ride)
	 */
	@Override
	public Ride createAccountRide(String accountId, Ride ride) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		ride.setAccountId(accountId);
		com.nebulent.vectura.data.model.mongodb.Ride mongoRide = DomainUtils.toRide(ride);
		mongoRide = getMongoRepository().getRideRepository().save(mongoRide);
		return DomainUtils.toRide(mongoRide);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AccountResource#findAccountRidesByDate(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Ride> findAccountRidesByDate(String accountId, String dateAsString) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		if(StringUtils.isBlank(dateAsString)){
			throw new BadRequestException(new StatusResponse(false, "Date is a required field.", null));
		}
		
		List<com.nebulent.vectura.data.model.mongodb.Ride> rides = getMongoRepository().getRideRepository().findByAccountUuidDateOrderByApptOnAsc(accountId, dateAsString);
		return DomainUtils.toRides(rides);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.RideResource#findRide(java.lang.String)
	 */
	@Override
	public Ride findRide(String id) {
		if(StringUtils.isBlank(id)){
			throw new BadRequestException(new StatusResponse(false, "Ride ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.Ride ride = getMongoRepository().getRideRepository().findOne(id);
		if(ride != null){
			return DomainUtils.toRide(ride);
		}
		throw new NotFoundException(new StatusResponse(false, "Ride not found by id:", id));
	}
}
