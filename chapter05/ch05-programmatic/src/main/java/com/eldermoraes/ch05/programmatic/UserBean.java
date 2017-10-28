package com.eldermoraes.ch05.programmatic;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;

/**
 *
 * @author eldermoraes
 */
@Stateful
public class UserBean {
    
    @RolesAllowed({Roles.ADMIN})
    public void adminOperation(){
        System.out.println("adminOperation executed");
    }
    
    @RolesAllowed({Roles.USER})
    public void userOperation(){
        System.out.println("userOperation executed");
    }

    @PermitAll
    public void everyoneCanDo(){
        System.out.println("everyoneCanDo executed");
    }

}
