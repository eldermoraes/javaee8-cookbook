package com.eldermoraes.ch08.decoupled.dosomethingwithuser;

import javax.annotation.PostConstruct;
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
@Path("doSomethingService")
public class DoSomethingService {
    
    private final String hostURI = "http://localhost:8080/";
    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target(hostURI + "ch08-decoupled-user/");
    }    
    
    @Path("doSomethingCoupled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doSomethingCoupled(String name, String email){
        WebTarget service = target.path("webresources/userService/getUserCoupled");
        service.queryParam("name", name);
        service.queryParam("email", email);

        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        return Response.ok(response.readEntity(String.class)).build();
    }
    
    @Path("doSomethingDecoupled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doSomethingDecoupled(User user){
        WebTarget service = target.path("webresources/userService/getUserDecoupled");

        Response response;
        try {
            response = service.request().header("User", Entity.json(user)).get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }

        return Response.ok(response.readEntity(String.class)).build();
    }    
}
