package com.eldermoraes.ch08.micro_x_mono.mono.service;

import com.eldermoraes.ch08.micro_x_mono.mono.bean.UserBean;
import com.eldermoraes.ch08.micro_x_mono.mono.entity.User;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
    
    @EJB
    private UserBean userBean;
    
    @GET
    @Path("get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id){
        return Response.ok(userBean.findById(id)).build();
    }
    
    @PUT
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response add(@PathParam("name") String name,
                        @PathParam("email") String email){
        User user = new User(name, email);
        userBean.add(user);
        return Response.accepted().build();
    }    
    
    @DELETE
    @Path("remove")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response remove(@PathParam("id") Long id){
        userBean.remove(userBean.findById(id));
        return Response.accepted().build();
    }
    
    
    
}
