package com.eldermoraes.ch03.rscdi;

import java.io.Serializable;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptor;

/**
 *
 * @author eldermoraes
 */
@ViewScoped
@Named
public class UserService implements Serializable{
    
    @Inject
    private User userProducer;
    
    @Inject
    private UserBean userBean;
    
    private User userFromBean;
    private User userLocal;
    
    @Inject
    private Event<User> userEvent;
    
    @Inject
    private void setUserLocal(){
        long ts = System.currentTimeMillis();
        userLocal = new User("Local" + ts,  "user" + ts + "@eldermoraes.com");        
    }
    
    public void loadUsers(){
        FacesContext.getCurrentInstance()
                .addMessage(null,
                new FacesMessage("userProducer: " + userProducer));
        
        userFromBean = userBean.getUser();
        FacesContext.getCurrentInstance()
                .addMessage(null,
                new FacesMessage("userBean: " + userFromBean));
        
        FacesContext.getCurrentInstance()
                .addMessage(null,
                new FacesMessage("userLocal: " + userLocal));  
        
        fireEvents(userProducer);
        fireEvents(userFromBean);
        fireEvents(userLocal);
        
    }
    
    private void fireEvents(User user){
        userEvent.fire(userProducer);
    }
    
    
    public void sendUserEvent(@Observes User user){
        FacesContext.getCurrentInstance()
                .addMessage(null,
                new FacesMessage("Observed: " + user));        
    }
    
    public void sendUserEventOrderedFirst(@Observes @Priority(Interceptor.Priority.APPLICATION + 100) User user){
        FacesContext.getCurrentInstance()
                .addMessage(null,
                new FacesMessage("ObservedOrderedFirst: " + user));        
    }

    public void sendUserEventOrderedSecond(@Observes @Priority(Interceptor.Priority.APPLICATION + 200) User user){
        FacesContext.getCurrentInstance()
                .addMessage(null,
                new FacesMessage("ObservedOrderedSecond: " + user));        
    }    
}
