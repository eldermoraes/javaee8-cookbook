package com.eldermoraes.ch08.micro_x_mono.micro.gateway;

import com.eldermoraes.ch08.micro_x_mono.micro.gateway.pojo.UserAddress;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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

    private final String hostURI = "http://localhost:8080/";
    private Client client;
    private WebTarget targetUser;
    private WebTarget targetAddress;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        targetUser = client.target(hostURI + "ch08-micro_x_mono-micro-user/");
        targetAddress = client.target(hostURI + "ch08-micro_x_mono-micro-address/");
    }
    
    @PreDestroy
    public void destroy(){
        client.close();
    }

    @GET
    @Path("getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        WebTarget service = targetUser.path("webresources/userService/get");

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        GatewayResponse gatewayResponse = new GatewayResponse();
        gatewayResponse.setResponse(response.readEntity(String.class));
        gatewayResponse.setFrom(targetUser.getUri().toString());

        return Response.ok(gatewayResponse).build();
    }

    @POST
    @Path("addAddress")
    @Produces(MediaType.APPLICATION_JSON)    
    public Response addAddress(UserAddress address) {
        WebTarget service = targetAddress.path("webresources/userAddressService/add");

        Response response;
        try {
            response = service.request().post(Entity.json(address));
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        return Response.fromResponse(response).build();
    }

}
