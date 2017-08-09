package com.eldermoraes.ch01.mvc;

import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {
    
    public User getUser(){
        return new User("Elder", "elder@eldermoraes.com");
    }
}
