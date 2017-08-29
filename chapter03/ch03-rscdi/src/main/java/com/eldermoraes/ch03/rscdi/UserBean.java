package com.eldermoraes.ch03.rscdi;

import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    public User getUser(){
        long ts = System.currentTimeMillis();
        return new User("Bean" + ts,  "user" + ts + "@eldermoraes.com");        
    }
}
