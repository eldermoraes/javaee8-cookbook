package com.eldermoraes.ch01.jsf;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserBean implements Serializable {
    
    private User user;
    
    public UserBean(){
        user = new User("Elder Moraes", "elder@eldermoraes.com");
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