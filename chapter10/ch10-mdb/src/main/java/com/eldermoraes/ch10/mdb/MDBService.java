package com.eldermoraes.ch10.mdb;

import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author eldermoraes
 */
@Stateless
@Path("mdbService")
public class MDBService {
    
    @Inject
    private Sender sender;
    
    public void mdbService(@Suspended AsyncResponse response){
        long id = new Date().getTime();
        sender.send(new User(id, "User " + id));
        response.resume("Message sent to the queue");
    }
}
