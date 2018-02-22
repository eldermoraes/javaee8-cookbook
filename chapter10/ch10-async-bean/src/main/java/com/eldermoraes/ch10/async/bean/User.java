package com.eldermoraes.ch10.async.bean;

import java.io.Serializable;

/**
 *
 * @author eldermoraes
 */
public class User implements Serializable{

    private Long id;
    private String name;
    
    public User(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
