package com.eldermoraes.ch01.mvc;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;

/**
 *
 * @author eldermoraes
 */

@Controller
@Path("userController")
public class UserController {
    
    @Inject
    Models models;
    
    @Inject
    UserBean userBean;
    
    @GET
    public String user(){
        models.put("user", userBean.getUser());
        return "/user.jsp";
    }
    
}