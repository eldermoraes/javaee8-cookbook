package com.eldermoraes.ch03.rscdi;

import java.io.Serializable;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Path("userservice")
public class UserService implements Serializable{
    
    @Inject
    private UserBean userBean;
    
    private User userLocal;
    
    @Inject
    private void setUserLocal(){
        long ts = System.currentTimeMillis();
        userLocal = new User("Local" + ts,  "user" + ts + "@eldermoraes.com");        
    }
    
    @GET
    @Path("getUserFromBean")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFromBean(){
        return Response.ok(userBean.getUser()).build();
    }

    @GET
    @Path("getUserFromLocal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFromLocal(){
        return Response.ok(userLocal).build();
    }    
    
}
