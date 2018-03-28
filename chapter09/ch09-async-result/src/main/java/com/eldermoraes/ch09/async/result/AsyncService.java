package com.eldermoraes.ch09.async.result;

import com.eldermoraes.ch09.async.result.client.AsyncResultClient;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Stateless
@Path("asyncService")
public class AsyncService {

    @Inject
    private AsyncResultClient client;

    @GET
    public void asyncService(@Suspended AsyncResponse response) {
        try{
            client.getResult().thenApply(this::readResponse).thenAccept(response::resume);
        } catch(Exception e){
            response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build());
        }        
    }

    private String readResponse(Response response) {
        return response.readEntity(String.class);
    }
}
