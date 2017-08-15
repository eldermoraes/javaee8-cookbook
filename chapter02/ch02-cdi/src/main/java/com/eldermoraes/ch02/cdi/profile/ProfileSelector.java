/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.cdi.profile;

import javax.enterprise.util.AnnotationLiteral;

/**
 *
 * @author eldermoraes
 */
public class ProfileSelector extends AnnotationLiteral implements Profile{

    private final ProfileType type;
    
    public ProfileSelector(ProfileType type){
        this.type = type;   
    }

    @Override
    public ProfileType value() {
        return type;
    }
    
    
}
