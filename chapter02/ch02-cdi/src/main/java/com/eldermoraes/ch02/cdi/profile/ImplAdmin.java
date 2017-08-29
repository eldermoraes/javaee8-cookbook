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
