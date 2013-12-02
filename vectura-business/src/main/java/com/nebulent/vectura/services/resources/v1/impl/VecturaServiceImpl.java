/**
 * 
 */
package com.nebulent.vectura.services.resources.v1.impl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nebulent.schema.software.vectura._1.Account;
import nebulent.schema.software.vectura._1.AddressInfo;
import nebulent.schema.software.vectura._1.ContactType;
import nebulent.schema.software.vectura._1.Patient;
import nebulent.schema.software.vectura._1.PhoneInfo;
import nebulent.schema.software.vectura._1.Place;
import nebulent.schema.software.vectura._1.Ride;
import nebulent.schema.software.vectura._1.Run;
import nebulent.schema.software.vectura._1.User;
import nebulent.schema.software.vectura._1.Vehicle;
import net.sourceforge.jgeocoder.AddressComponent;
import net.sourceforge.jgeocoder.us.AddressParser;
import net.sourceforge.jgeocoder.us.AddressStandardizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nebulent.vectura.data.model.mongodb.core.Contact;
import com.nebulent.vectura.persistence.mongodb.CoreRepository;
import com.nebulent.vectura.services.MapService;
import com.nebulent.vectura.services.resources.BadRequestException;
import com.nebulent.vectura.services.resources.NotFoundException;
import com.nebulent.vectura.services.resources.StatusResponse;
import com.nebulent.vectura.services.resources.v1.AccountResource;
import com.nebulent.vectura.services.resources.v1.PlaceResource;
import com.nebulent.vectura.services.resources.v1.RideResource;
import com.nebulent.vectura.services.resources.v1.RunResource;
import com.nebulent.vectura.services.utils.DomainUtils;

/**
 * @author mfedorov
 *
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/")
public class VecturaServiceImpl implements AccountResource, RunResource, PlaceResource, RideResource{

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
	public Place findLocation(String id) {
		if(StringUtils.isBlank(id)){
			throw new BadRequestException(new StatusResponse(false, "Location ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.Place location = getMongoRepository().getPlaceRepository().findOne(id);
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
		
		com.nebulent.vectura.data.model.mongodb.core.AddressInfo dbAddress = DomainUtils.toAddress(address);
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
		
		com.nebulent.vectura.data.model.mongodb.core.PhoneInfo dbPhone = DomainUtils.toPhone(phone);
		getMongoRepository().addAccountPhone(accountId, dbPhone);
		return DomainUtils.toPhone(dbPhone);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AccountResource#addAccountVehicle(java.lang.String, nebulent.schema.software.vectura._1.Vehicle)
	 */
	@Override
	public Vehicle addAccountVehicle(String accountId, Vehicle vehicle) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.core.Vehicle dbVehicle = DomainUtils.toVehicle(vehicle);
		getMongoRepository().addAccountVehicle(accountId, dbVehicle);
		return DomainUtils.toVehicleType(dbVehicle);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AccountResource#addAccountUser(java.lang.String, nebulent.schema.software.vectura._1.User)
	 */
	@Override
	public User addAccountUser(String accountId, User user) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.core.User dbUser = DomainUtils.toUser(user);
		getMongoRepository().addAccountUser(accountId, dbUser);
		return DomainUtils.toUser(dbUser);
	}

	@Override
	public Patient addAccountPatient(String accountId, Patient patient) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		com.nebulent.vectura.data.model.mongodb.core.Patient dbPatient = DomainUtils.toPatient(patient);
		getMongoRepository().addAccountPatient(accountId, dbPatient);
		return DomainUtils.toPatient(dbPatient);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AccountResource#createAccountPlace(java.lang.String, nebulent.schema.software.vectura._1.Place)
	 */
	@Override
	public Place createAccountPlace(String accountId, Place placeType) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		placeType.setAccountId(accountId);
		com.nebulent.vectura.data.model.mongodb.Place location = DomainUtils.toLocation(placeType);
		location.setAccountUuid(accountId);
		
		Map<AddressComponent, String> parsedAddr  = AddressParser.parseAddress(location.getAddress().toSingleLine());
		String normalizedAddress = AddressStandardizer.toSingleLine(parsedAddr);
		int addressHash = new HashCodeBuilder().append(normalizedAddress).toHashCode();
		
		if(addressHash != 0){
			System.out.println("Trying to find by address hash:" + addressHash + " and " + location.getAddress().toString());
			com.nebulent.vectura.data.model.mongodb.Place placeByHash = getMongoRepository().findPlaceByAccountUuidAndAddressHash(accountId, addressHash);
			if(placeByHash == null){
				AddressInfo addressType = mapService.getLocationByAddress(location.getAddress().toString());
				if(addressType != null){
					com.nebulent.vectura.data.model.mongodb.core.AddressInfo addressInfo = DomainUtils.toAddress(addressType);
					if(logger.isDebugEnabled()){
						logger.debug("Adding location with address:" + addressInfo);
					}
					if(addressInfo != null && StringUtils.isNotBlank(addressInfo.getAddressLine1())){
						location.setAddress(addressInfo);
						location.getAddress().setHash(addressHash);
						location.setLocation(location.getAddress().getLocation());
						location = getMongoRepository().getPlaceRepository().save(location);
					}
				}
			}
			else{
				System.out.println("Found by address hash:" + addressHash + " and " + placeByHash.toString());
				
				location = placeByHash;
			}
		}
		
		return DomainUtils.toLocation(location);
	}

	/* (non-Javadoc)
	 * @see com.nebulent.vectura.services.resources.v1.AccountResource#searchAccountPlaces(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Place> searchAccountPlaces(String accountId, String addressHash) {
		if(StringUtils.isBlank(accountId)){
			throw new BadRequestException(new StatusResponse(false, "Account ID is a required field.", null));
		}
		
		List<com.nebulent.vectura.data.model.mongodb.Place> locations = getMongoRepository().getPlaceRepository().findByAccountUuid(accountId);
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
		ride = DomainUtils.toRide(mongoRide);
		
		// Create pick-up location.
		Place place = new Place();
		place.setAddress(ride.getDropOffAddress());
		Place dropoffPlace = createAccountPlace(accountId, place);
		if(dropoffPlace != null){
			ride.setDropOffAddress(dropoffPlace.getAddress());
		}
		
		// Create drop-off location.
		place = new Place();
		place.setAddress(ride.getPickupAddress());
		Place pickupPlace = createAccountPlace(accountId, place);
		if(pickupPlace != null){
			ride.setPickupAddress(pickupPlace.getAddress());
		}
		
		return ride;
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
		
		List<com.nebulent.vectura.data.model.mongodb.Ride> rides = getMongoRepository().getRideRepository().findByAccountUuidAndRideDateAsStringOrderByApptOnAsc(accountId, dateAsString);
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
