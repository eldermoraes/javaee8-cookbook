/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.cdi.profile;

import javax.enterprise.inject.Default;

/**
 *
 * @author eldermoraes
 */
@Profile(ProfileType.OPERATOR)
@Default
public class ImplOperator implements UserProfile{

    @Override
    public ProfileType type() {
        System.out.println("User is operator");
        return ProfileType.OPERATOR;
    }
    
}
