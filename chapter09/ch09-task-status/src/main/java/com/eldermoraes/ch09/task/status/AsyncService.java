package com.eldermoraes.ch09.task.status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
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

    @Resource
    private ManagedExecutorService executor;

    @GET
    public void asyncService(@Suspended AsyncResponse response) {
        int i = 0;

        List<User> usersFound = new ArrayList<>();
        while (i < 4) {
            Future<User> result = executor.submit(new AsyncTask());

            while (!result.isDone()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            try {
                usersFound.add(result.get());
            } catch (InterruptedException | ExecutionException ex) {
                System.err.println(ex.getMessage());
            }

            i++;
        }

        response.resume(Response.ok(usersFound).build());
    }

}
