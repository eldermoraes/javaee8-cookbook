package com.eldermoraes.ch03.jsonb;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 *
 * @author eldermoraes
 */
@ViewScoped
@Named
public class UserView implements Serializable{
    
    private String json;
    
    public void loadUser(){
        long now = System.currentTimeMillis();
        User user = new User(now, 
                "User" + now, 
                "user" + now + "@eldermoraes.com",
                Math.random(),
                new Date());
        
        Jsonb jb = JsonbBuilder.create();
        json = jb.toJson(user);
        try {
            jb.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
