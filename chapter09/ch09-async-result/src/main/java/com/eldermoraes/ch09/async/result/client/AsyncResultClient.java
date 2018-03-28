package com.eldermoraes.ch09.async.result.client;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class AsyncResultClient {

    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        target = client.target("http://localhost:8080/ch09-async-result/userService");
    }
    
    @PreDestroy
    public void destroy(){
        client.close();
    }
    
    public CompletionStage<Response> getResult(){
        return target.request(MediaType.APPLICATION_JSON).rx().get();
    }
    
}
