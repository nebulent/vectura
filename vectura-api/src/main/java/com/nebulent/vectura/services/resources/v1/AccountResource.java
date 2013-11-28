package com.nebulent.vectura.services.resources.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import nebulent.schema.software.vectura._1.Account;
import nebulent.schema.software.vectura._1.AddressInfo;
import nebulent.schema.software.vectura._1.ContactType;
import nebulent.schema.software.vectura._1.Patient;
import nebulent.schema.software.vectura._1.PhoneInfo;
import nebulent.schema.software.vectura._1.Place;
import nebulent.schema.software.vectura._1.Ride;
import nebulent.schema.software.vectura._1.User;
import nebulent.schema.software.vectura._1.Vehicle;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.wordnik.swagger.annotations.Api;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
@Api(value="/accounts", description="Account API")
public interface AccountResource {
	
	@Descriptions({
        @Description(value = "Gets account by accountId", target = DocTarget.METHOD),
        @Description(value = "Returns found account", target = DocTarget.RETURN),
        @Description(value = "String accountIdOrEmail", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/accounts/{accountId}", target = DocTarget.RESOURCE)
    })
    @GET
    @Path("accounts/{accountId}")
    Account findAccount(@Description("String accountId") @PathParam("accountId") String accountId);
	
    @Descriptions({
        @Description(value = "Add address to account", target = DocTarget.METHOD),
        @Description(value = "Returns added address", target = DocTarget.RETURN),
        @Description(value = "AddressInfo address", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/addresses", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/addresses")
    AddressInfo addAccountAddress(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("AddressInfo address") AddressInfo address);
    
    @Descriptions({
        @Description(value = "Add contact to account", target = DocTarget.METHOD),
        @Description(value = "Returns added contact", target = DocTarget.RETURN),
        @Description(value = "ContactType contact", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/contacts", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/contacts")
    ContactType addAccountContact(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("ContactType contact") ContactType contact);
    
    @Descriptions({
        @Description(value = "Add phone to account", target = DocTarget.METHOD),
        @Description(value = "Returns added phone", target = DocTarget.RETURN),
        @Description(value = "PhoneInfo phone", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/phones", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/phones")
    PhoneInfo addAccountPhone(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("PhoneInfo phone") PhoneInfo phone);
    
    @Descriptions({
        @Description(value = "Add vehicle to account", target = DocTarget.METHOD),
        @Description(value = "Returns added vehicle", target = DocTarget.RETURN),
        @Description(value = "Vehicle vehicle", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/vehicles", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/vehicles")
    Vehicle addAccountVehicle(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("Vehicle vehicles") Vehicle vehicle);
    
    @Descriptions({
        @Description(value = "Add user to account", target = DocTarget.METHOD),
        @Description(value = "Returns added user", target = DocTarget.RETURN),
        @Description(value = "User user", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/users", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/users")
    User addAccountUser(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("User user") User user);
    
    /*@Descriptions({
        @Description(value = "Add driver to account", target = DocTarget.METHOD),
        @Description(value = "Returns added driver", target = DocTarget.RETURN),
        @Description(value = "Driver driver", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/drivers", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/drivers")
    Driver addAccountDriver(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("User user") Driver driver);
    */
    @Descriptions({
        @Description(value = "Add patient to account", target = DocTarget.METHOD),
        @Description(value = "Returns added patient", target = DocTarget.RETURN),
        @Description(value = "Patient patient", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/patients", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/patients")
    Patient addAccountPatient(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("Patient patient") Patient patient);
    
    @Descriptions({
        @Description(value = "Add place to account", target = DocTarget.METHOD),
        @Description(value = "Returns added place", target = DocTarget.RETURN),
        @Description(value = "Place place", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/places", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/places")
    Place createAccountPlace(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("Place place") Place place);
    
    @Descriptions({
        @Description(value = "Add ride for account", target = DocTarget.METHOD),
        @Description(value = "Returns added ride", target = DocTarget.RETURN),
        @Description(value = "Ride ride", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/rides", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/rides")
    Ride createAccountRide(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("Ride ride") Ride ride);
    
    @Descriptions({
        @Description(value = "Gets list of locations by account", target = DocTarget.METHOD),
        @Description(value = "Returns list of locations", target = DocTarget.RETURN),
        @Description(value = "String account ID", target = DocTarget.REQUEST),
        @Description(value = "String address hash to search by", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/accounts/{accountId}/locations?addressHash={addressHash}", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("accounts/{accountId}/places")
	List<Place> searchAccountPlaces(
			@Description("String accountId") @PathParam("accountId") String accountId,
			@Description("String addressHash") @QueryParam("addressHash") String addressHash);
    
    @Descriptions({
        @Description(value = "Gets list of rides by account", target = DocTarget.METHOD),
        @Description(value = "Returns list of rides", target = DocTarget.RETURN),
        @Description(value = "String account ID", target = DocTarget.REQUEST),
        @Description(value = "String date to search by", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/accounts/{accountId}/rides?date={date}", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("accounts/{accountId}/rides")
	List<Ride> findAccountRidesByDate(
			@Description("String accountId") @PathParam("accountId") String accountId,
			@Description("String date") @QueryParam("date") String date);
    
}
