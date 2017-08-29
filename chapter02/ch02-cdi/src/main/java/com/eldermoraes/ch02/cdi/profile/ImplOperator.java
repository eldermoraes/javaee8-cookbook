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
