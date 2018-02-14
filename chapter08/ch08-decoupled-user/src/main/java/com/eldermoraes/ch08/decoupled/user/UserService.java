package com.eldermoraes.ch08.decoupled.user;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Path("userService")
public class UserService {
    
    @GET
    @Path("getUserCoupled/{name}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCoupled(
            @PathParam("name") String name, 
            @PathParam("email") String email){
        //GET USER CODE
        
        return Response.ok().build();
    }
    
    @GET
    @Path("getUserDecoupled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDecoupled(@HeaderParam("User") User user){
        //GET USER CODE
        
        return Response.ok().build();
    }
}
