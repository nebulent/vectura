/**
 * 
 */
package com.nebulent.vectura.services.resources.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nebulent.schema.software.vectura._1.Place;
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
@Api(value="/places", description="Places API")
public interface PlaceResource {
	
	@Descriptions({
        @Description(value = "Gets place by id", target = DocTarget.METHOD),
        @Description(value = "Returns found place", target = DocTarget.RETURN),
        @Description(value = "String id", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/places/{id}", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("places/{id}")
	Place findLocation(@Description("String id") @PathParam("id") String id);
	
	@Descriptions({
        @Description(value = "Gets runs by place id", target = DocTarget.METHOD),
        @Description(value = "Returns found runs", target = DocTarget.RETURN),
        @Description(value = "String id", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/places/{id}/runs", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("places/{id}/runs")
	List<Run> findRunsByLocation(@Description("String id") @PathParam("id") String id);
}
