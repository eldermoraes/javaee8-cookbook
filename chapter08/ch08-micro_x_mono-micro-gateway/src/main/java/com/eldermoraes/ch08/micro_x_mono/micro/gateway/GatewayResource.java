package com.eldermoraes.ch08.micro_x_mono.micro.gateway;

import com.eldermoraes.ch08.micro_x_mono.micro.gateway.pojo.UserAddress;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Consumes(MediaType.APPLICATION_JSON)
@Path("gatewayResource")
@RequestScoped
public class GatewayResource {

    @Inject
    @DiscoverService(value = "user-service", version = "1.0.x", environment = "dev")
    private WebTarget targetUser;

    @Inject
    @DiscoverService(value = "address-service", version = "1.0.x", environment = "dev")
    private WebTarget targetAddress;

    @GET
    @Path("getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        WebTarget service = targetUser.path("webresources/get");

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        ProxiedResponse proxiedResponse = new ProxiedResponse();
        proxiedResponse.setResponse(response.readEntity(String.class));
        proxiedResponse.setProxiedFrom(targetUser.getUri().toString());

        return Response.ok(proxiedResponse).build();
    }
    
    @POST
    public Response addNewCustomer(UserAddress address) {
        WebTarget service = targetAddress.path("webresources/add");

        Response response;
        try {
            response = service.request().post(Entity.json(address));
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        return Response.fromResponse(response).build();
    }    

}
