package com.eldermoraes.ch10.event.observer;

import java.util.Date;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
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
    private Event<User> event;
    
    private AsyncResponse response;
    
    @GET
    public void asyncService(@Suspended AsyncResponse response){
        long id  = new Date().getTime();
        this.response = response;
        event.fireAsync(new User(id, "User " + id));
    }
    
    public void onFireEvent(@ObservesAsync User user){
        response.resume(Response.ok(user).build());
    }
}
