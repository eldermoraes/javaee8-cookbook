package com.eldermoraes.ch09.scheduled.task.remote;

import com.eldermoraes.ch09.scheduled.task.User;
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
            long id = new Date().getTime();
            return new User(id, "Error " + id);
        }
    }
}
