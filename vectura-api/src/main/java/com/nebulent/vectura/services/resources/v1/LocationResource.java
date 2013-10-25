/**
 * 
 */
package com.nebulent.vectura.services.resources.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nebulent.schema.software.vectura._1.Location;
import nebulent.schema.software.vectura._1.Run;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.wordnik.swagger.annotations.Api;

/**
 * @author mfedorov
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
@Api(value="/locations", description="Location API")
public interface LocationResource {
	
	@Descriptions({
        @Description(value = "Gets location by location id", target = DocTarget.METHOD),
        @Description(value = "Returns found location", target = DocTarget.RETURN),
        @Description(value = "String id", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/locations/{id}", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("locations/{id}")
	Location findLocation(@Description("String id") @PathParam("id") String id);
	
	@Descriptions({
        @Description(value = "Gets runs by location id", target = DocTarget.METHOD),
        @Description(value = "Returns found runs", target = DocTarget.RETURN),
        @Description(value = "String id", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/locations/{id}/runs", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("locations/{id}/runs")
	List<Run> findRunsByLocation(@Description("String id") @PathParam("id") String id);
	
	@Descriptions({
        @Description(value = "Creates location and stores it", target = DocTarget.METHOD),
        @Description(value = "Returns created location with ID", target = DocTarget.RETURN),
        @Description(value = "String account ID", target = DocTarget.REQUEST),
        @Description(value = "Location location", target = DocTarget.REQUEST),
        @Description(value = "POST http://{host}/api/accounts/{accountId}/locations", target = DocTarget.RESOURCE)
    })
	@POST
	@Path("locations")
	Location createLocation(
			@Description("String accountId") @PathParam("accountId") String accountId,
			@Description("Location location") Location location);
}
