package com.eldermoraes.ch01.jsf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class UserBean implements Serializable {
    
    private User user;
    
    @PostConstruct
    public void init(){
        user = new User("Elder Moraes", "elder@eldermoraes.com");
    }
    
    public UserBean(){
        
    }

    public void userAction(){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Name|Password welformed"));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}