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
import nebulent.schema.software.vectura._1.Location;
import nebulent.schema.software.vectura._1.Patient;
import nebulent.schema.software.vectura._1.PhoneInfo;
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
        @Description(value = "POST http://{host}/api/accounts", target = DocTarget.RESOURCE)
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
        @Description(value = "POST http://{host}/api/contacts", target = DocTarget.RESOURCE)
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
        @Description(value = "POST http://{host}/api/phones", target = DocTarget.RESOURCE)
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
        @Description(value = "POST http://{host}/api/vehicles", target = DocTarget.RESOURCE)
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
        @Description(value = "POST http://{host}/api/users", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/users")
    User addAccountUser(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("User user") User user);
    
    @Descriptions({
        @Description(value = "Add patient to account", target = DocTarget.METHOD),
        @Description(value = "Returns added patient", target = DocTarget.RETURN),
        @Description(value = "Patient patient", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/patients", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/patients")
    Patient addAccountPatient(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("Patient patient") Patient patient);
    
    @Descriptions({
        @Description(value = "Add locations to account", target = DocTarget.METHOD),
        @Description(value = "Returns added locations", target = DocTarget.RETURN),
        @Description(value = "Location locations", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/locations", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts/{accountId}/locations")
    Location addAccountLocation(
    		@Description("String accountId") @PathParam("accountId") String accountId,
    		@Description("Location locations") Location location);
    
    @Descriptions({
        @Description(value = "Gets list of locations by account", target = DocTarget.METHOD),
        @Description(value = "Returns list of locations", target = DocTarget.RETURN),
        @Description(value = "String account ID", target = DocTarget.REQUEST),
        @Description(value = "String address hash to search by", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/accounts/{accountId}/locations", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("accounts/{accountId}/locations")
	List<Location> searchAccountLocations(
			@Description("String accountId") @PathParam("accountId") String accountId,
			@Description("String addressHash") @QueryParam("addressHash") String addressHash);
    
}
