package com.eldermoraes.ch09.async.result;

import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    public User getSlowUser(){
        try {
            Thread.sleep(5000L);
            return new User(1L,"Elder Moraes");
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
