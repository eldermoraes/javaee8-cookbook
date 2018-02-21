package com.eldermoraes.ch09.scheduled.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
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

    @Resource(name = "LocalManagedScheduledExecutorService")
    private ManagedScheduledExecutorService executor;

    @GET
    public void asyncService(@Suspended AsyncResponse response) {

        ScheduledFuture<User> result = executor.schedule(new AsyncTask(), 5, TimeUnit.SECONDS);
        
        while (!result.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }

        try {
            response.resume(Response.ok(result.get()).build());
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex.getMessage());
            response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build());
        }
        
    }

}
