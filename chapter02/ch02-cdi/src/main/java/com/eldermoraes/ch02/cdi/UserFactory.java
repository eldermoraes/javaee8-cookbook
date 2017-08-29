package com.eldermoraes.ch02.cdi;

import java.io.Serializable;
import javax.enterprise.inject.Produces;

/**
 *
 * @author eldermoraes
 */
public class UserFactory implements Serializable{

    @Produces
    public User getUser() {
        return new User("Elder Moraes", "elder@eldermoraes.com");
    }

}
