package com.eldermoraes.ch09.proxy.task;

import java.util.concurrent.Future;
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
    private ExecutorProxy executor;

    @GET
    public void asyncService(@Suspended AsyncResponse response) {
        Future<User> result = executor.submit(new AsyncTask());
        response.resume(Response.ok(result).build());
    }

}
