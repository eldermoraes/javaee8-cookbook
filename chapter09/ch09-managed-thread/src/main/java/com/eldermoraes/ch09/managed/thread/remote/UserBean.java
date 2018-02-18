package com.eldermoraes.ch09.managed.thread.remote;

import com.eldermoraes.ch09.managed.thread.User;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.ws.rs.GET;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    @GET
    public User getUser(){
        try {
            TimeUnit.SECONDS.sleep(5);
            long id = new Date().getTime();
            return new User(id, "User " + id);
        } catch (InterruptedException ex) {
            long id = new Date().getTime();
            return new User(id, "Error " + id);
        }
    }
}
