package com.nebulent.vectura.services.resources.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nebulent.schema.software.vectura._1.Account;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public interface AdminResource {
	
	@Descriptions({
        @Description(value = "Updates account by accountId", target = DocTarget.METHOD),
        @Description(value = "Returns updated account", target = DocTarget.RETURN),
        @Description(value = "String accountId, Account account", target = DocTarget.REQUEST),
        @Description(value = "PUT http://{host}/api/accounts/{accountId}", target = DocTarget.RESOURCE)
    })
    @PUT
    @Path("accounts/{accountId}")
    Account updateAccount(
    		@Description("String accountId") @PathParam("accountId") String accountId
    		, @Description("Account account") Account account);

    @Descriptions({
        @Description(value = "Creates account", target = DocTarget.METHOD),
        @Description(value = "Returns created account", target = DocTarget.RETURN),
        @Description(value = "Account account", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts", target = DocTarget.RESOURCE)
    })
    @POST
    @Path("accounts")
    Account createAccount(@Description("Account account") Account account);

    @Descriptions({
        @Description(value = "Removes account by accountId", target = DocTarget.METHOD),
        @Description(value = "Returns StatusResponse", target = DocTarget.RETURN),
        @Description(value = "String accountId", target = DocTarget.REQUEST),
        @Description(value = "DELETE http://{host}/api/accounts/{accountId}", target = DocTarget.RESOURCE)
    })
    @DELETE
    @Path("accounts/{accountId}")
    Account removeAccount(@Description("String accountId") @PathParam("accountId") String accountId);
}
