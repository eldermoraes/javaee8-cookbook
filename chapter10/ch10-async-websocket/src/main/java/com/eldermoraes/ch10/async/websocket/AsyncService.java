package com.eldermoraes.ch10.async.websocket;

import java.util.Date;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author eldermoraes
 */

@Stateless
@Path("asyncService")
public class AsyncService {
    
    @GET
    public void asyncService(@Suspended AsyncResponse response){
        AsyncClient client = new AsyncClient(response);
        client.connect();
        client.send("Message from client " + new Date().getTime());
        client.close();
    }
}
