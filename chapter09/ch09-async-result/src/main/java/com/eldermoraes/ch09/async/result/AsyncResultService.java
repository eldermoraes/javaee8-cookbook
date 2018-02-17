package com.eldermoraes.ch09.async.result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Path("asyncResultService")
public class AsyncResultService {

    @EJB
    private UserBean userBean;

    @GET
    @Path("asyncResult")
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncResult(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.setTimeout(10000, TimeUnit.MILLISECONDS);
        CompletableFuture
                .runAsync(() -> 
                        Response.ok(userBean.getSlowUser()).build())
                .thenApply((result) -> 
                        asyncResponse.resume(result));
    }
}
