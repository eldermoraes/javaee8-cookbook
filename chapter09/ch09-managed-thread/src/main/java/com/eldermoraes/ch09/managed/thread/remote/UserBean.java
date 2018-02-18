package com.eldermoraes.ch09.managed.thread.remote;

import com.eldermoraes.ch09.managed.thread.User;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    @GET
    public Response getUser(){
        try {
            TimeUnit.SECONDS.sleep(5);
            long id = new Date().getTime();
            return Response.ok(new User(id, "User " + id)).build();
        } catch (InterruptedException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }
}
