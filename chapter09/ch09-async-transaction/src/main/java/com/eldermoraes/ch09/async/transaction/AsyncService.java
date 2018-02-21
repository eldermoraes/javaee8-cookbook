package com.eldermoraes.ch09.async.transaction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Path("asyncService")
@RequestScoped
public class AsyncService {
    
    private AsyncTask asyncTask;
    
    @Resource(name = "LocalManagedExecutorService")
    private ManagedExecutorService executor;   
    
    @PostConstruct
    public void init(){
        asyncTask = new AsyncTask();
    }
    
    @GET
    public void asyncService(@Suspended AsyncResponse response){
        
        Future<User> result = executor.submit(asyncTask);
        
        while(!result.isDone()){
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
