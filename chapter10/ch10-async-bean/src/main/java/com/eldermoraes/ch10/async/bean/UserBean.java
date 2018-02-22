package com.eldermoraes.ch10.async.bean;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    @Asynchronous
    public Future<User> getUser(){
        long id = new Date().getTime();
        User user = new User(id, "User " + id);
        return new AsyncResult(user);
    }
    
    @Asynchronous
    public void doSomeSlowStuff(User user){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
