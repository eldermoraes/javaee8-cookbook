package com.eldermoraes.ch08.micro_x_mono.mono.service;

import com.eldermoraes.ch08.micro_x_mono.mono.bean.UserAddressBeanMock;
import com.eldermoraes.ch08.micro_x_mono.mono.bean.UserBean;
import com.eldermoraes.ch08.micro_x_mono.mono.entity.UserAddress;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Path("userAddressService")
public class UserAddressService {
    
    @EJB
    private UserAddressBeanMock userAddressBean;
    
    @GET
    @Path("findById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        return Response.ok(userAddressBean.findById(id)).build();
    }
    
    @GET
    @Path("get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        return Response.ok(userAddressBean.get()).build();
    }    
    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response add(UserAddress address){
        userAddressBean.add(address);
        return Response.accepted().build();
    }    
    
    @DELETE
    @Path("remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response remove(@PathParam("id") Long id){
        userAddressBean.remove(userAddressBean.findById(id));
        return Response.accepted().build();
    }
    
}
