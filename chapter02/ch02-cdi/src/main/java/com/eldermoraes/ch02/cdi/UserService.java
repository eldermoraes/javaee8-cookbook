/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.cdi;

import com.eldermoraes.ch02.cdi.profile.Profile;
import com.eldermoraes.ch02.cdi.profile.ProfileType;
import com.eldermoraes.ch02.cdi.profile.UserProfile;
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
    private UserProfile userAdmin;
    
    @Inject
    @Profile(ProfileType.OPERATOR)
    private UserProfile userOperator;
    
}
