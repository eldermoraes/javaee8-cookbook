/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.cdi.profile;

/**
 *
 * @author eldermoraes
 */
@Profile(ProfileType.ADMIN)
public class ImplAdmin implements UserProfile{

    @Override
    public ProfileType type() {
        System.out.println("User is admin");
        return ProfileType.ADMIN;
    }
    
}
