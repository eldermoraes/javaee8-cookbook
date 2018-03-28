package com.eldermoraes.ch10.async.jaxrs;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
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
    
    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        target = client.target("http://localhost:8080/ch10-async-jaxrs/remoteUser");
    }
    
    @PreDestroy
    public void destroy(){
        client.close();
    }
    
    @GET
    public void asyncService(@Suspended AsyncResponse response){
        target.request().async().get(new InvocationCallback<Response>() {
            @Override
            public void completed(Response rspns) {
                response.resume(rspns);
            }

            @Override
            public void failed(Throwable thrwbl) {
                response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(thrwbl.getMessage()).build());
            }
        });
                
    }
    
}
