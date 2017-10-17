package com.eldermoraes.ch05.authorization;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

/**
 *
 * @author eldermoraes
 */
public class UserActivity {
    
    @RolesAllowed({"role1"})
    public static String role1Allowed(){
        return "role1Allowed executed";
    }
    
    @RolesAllowed({"role2"})
    public static String role2Allowed(){
        return "role2Allowed executed";
    }

    @RolesAllowed({"role3"})
    public static String role3Allowed(){
        return "role3Allowed executed";
    }

    @PermitAll
    public static String anonymousAllowed(){
        return "anonymousAllowed executed";
    }

    @DenyAll
    public static String noOneAllowed(){
        return "noOneAllowed executed";
    }    
    
}
