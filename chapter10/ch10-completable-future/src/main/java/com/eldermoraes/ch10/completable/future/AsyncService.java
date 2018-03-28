package com.eldermoraes.ch10.completable.future;

import java.util.concurrent.CompletableFuture;
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
    private UserBean userBean;

    @GET
    public void asyncService(@Suspended AsyncResponse response) {
        CompletableFuture
                .supplyAsync(() -> userBean.getUser())
                .thenAcceptAsync((u) -> {
                    response.resume(Response.ok(u).build());
                }).exceptionally((t) -> {
                    response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(t.getMessage()).build());
                    return null;
                });
    }
}
