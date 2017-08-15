/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.cdi;

import com.eldermoraes.ch02.cdi.profile.Profile;
import com.eldermoraes.ch02.cdi.profile.ProfileSelector;
import com.eldermoraes.ch02.cdi.profile.ProfileType;
import com.eldermoraes.ch02.cdi.profile.UserProfile;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author eldermoraes
 */
public class UserService {
    
    @Inject
    private User user;
    
    @Inject
    @Profile(ProfileType.ADMIN)
    private UserProfile userProfileAdmin;
    
    @Inject
    @Profile(ProfileType.OPERATOR)
    private UserProfile userProfileOperator;
    
    @Inject
    private UserProfile userProfileDefault;
    
    @Inject
    @Any
    private Instance profiles;
    
    @Inject
    private Event userEvent;
    
    public ProfileType getProfileRuntime(ProfileType type){
        Profile profile = (Profile)profiles.select(new ProfileSelector(type)).get();
        fireUserEvents();
        return profile.value();
    }
    
    public ProfileType getProfileAdmin(){
        return userProfileAdmin.type();
    }
    
    public ProfileType getProfileOperator(){
        return userProfileOperator.type();
    }

    public ProfileType getProfileDefault(){
        return userProfileDefault.type();
    }    
    
    private void fireUserEvents(){
        userEvent.fire(user);
        userEvent.fireAsync(user);
    }
    
    public void sendUserNotification(@Observes User user){
        System.out.println("sendUserNotification: " + user);
    }
    
    public void sendUserNotificationAsync(@ObservesAsync User user){
        System.out.println("sendUserNotificationAsync: " + user);
    }    
    
}
