package com.eldermoraes.ch09.task.status.remote;

import com.eldermoraes.ch09.task.status.User;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author eldermoraes
 */
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
