package com.eldermoraes.ch08.decoupled.user;

import java.io.Serializable;

/**
 *
 * @author eldermoraes
 */
public class User implements Serializable{

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
