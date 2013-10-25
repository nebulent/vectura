/**
 * 
 */
package com.nebulent.vectura.services.resources.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
@Api(value="/runs", description="Run API")
public interface RunResource {
	
	@Descriptions({
        @Description(value = "Gets run by ID", target = DocTarget.METHOD),
        @Description(value = "Returns found run", target = DocTarget.RETURN),
        @Description(value = "String id", target = DocTarget.REQUEST),
        @Description(value = "GET http://{host}/api/v1/runs/{id}", target = DocTarget.RESOURCE)
    })
	@GET
	@Path("runs/{id}")
	Run findRun(@Description("String id") @PathParam("id") String id);
}
