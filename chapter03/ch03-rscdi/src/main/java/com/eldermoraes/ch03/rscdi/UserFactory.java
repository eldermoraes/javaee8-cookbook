package com.eldermoraes.ch03.rscdi;

import java.io.Serializable;
import javax.enterprise.inject.Produces;

/**
 *
 * @author eldermoraes
 */
public final class UserFactory implements Serializable{
    
    @Produces
    public User getUser(){
        long ts = System.currentTimeMillis();
        return new User("Producer" + ts,  "user" + ts + "@eldermoraes.com");
    }
    
}
