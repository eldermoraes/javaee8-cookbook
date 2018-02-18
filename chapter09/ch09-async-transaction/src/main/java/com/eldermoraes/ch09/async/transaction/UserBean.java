package com.eldermoraes.ch09.async.transaction;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    public User getUser(){
        try {
            TimeUnit.SECONDS.sleep(5);
            long id = new Date().getTime();
            return new User(id, "User " + id);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            long id = new Date().getTime();
            return new User(id, "Error " + id);
        }
    }
}
