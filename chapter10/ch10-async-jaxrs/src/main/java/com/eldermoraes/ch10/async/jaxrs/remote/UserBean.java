package com.eldermoraes.ch10.async.jaxrs.remote;

import com.eldermoraes.ch10.async.jaxrs.User;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Stateless
@Path("remoteUser")
public class UserBean {

    @GET
    public Response remoteUser() {
        long id = new Date().getTime();
        try {
            TimeUnit.SECONDS.sleep(5);
            return Response.ok(new User(id, "User " + id)).build();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            return Response.ok(new User(id, "Error " + id)).build();
        }
    }

}
