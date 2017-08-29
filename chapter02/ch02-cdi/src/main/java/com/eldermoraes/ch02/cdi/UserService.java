package com.eldermoraes.ch02.cdi;

import com.eldermoraes.ch02.cdi.profile.Profile;
import com.eldermoraes.ch02.cdi.profile.ProfileType;
import com.eldermoraes.ch02.cdi.profile.UserProfile;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Path("userservice/")
@RequestScoped
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
    private Event<User> userEvent;
    
    @GET
    @Path("getUser")
    public Response getUser(@Context HttpServletRequest request, 
            @Context HttpServletResponse response) throws ServletException, IOException{
        
        request.setAttribute("result", user);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
        return Response.ok().build();
    }    
    
    @GET
    @Path("getProfileAdmin")
    public Response getProfileAdmin(@Context HttpServletRequest request, 
            @Context HttpServletResponse response) throws ServletException, IOException{
        
        request.setAttribute("result", fireUserEvents(userProfileAdmin.type()));
        request.getRequestDispatcher("/result.jsp").forward(request, response);
        return Response.ok().build();
    }
    
    @GET
    @Path("getProfileOperator")
    public Response getProfileOperator(@Context HttpServletRequest request, 
            @Context HttpServletResponse response) throws ServletException, IOException{
        
        request.setAttribute("result", fireUserEvents(userProfileOperator.type())); 
        request.getRequestDispatcher("/result.jsp").forward(request, response);
        return Response.ok().build();
    }

    @GET
    @Path("getProfileDefault")
    public Response getProfileDefault(@Context HttpServletRequest request, 
            @Context HttpServletResponse response) throws ServletException, IOException{
        
        request.setAttribute("result", fireUserEvents(userProfileDefault.type())); 
        request.getRequestDispatcher("/result.jsp").forward(request, response);
        return Response.ok().build();
    }    
    
    private ProfileType fireUserEvents(ProfileType type){
        userEvent.fire(user);
        userEvent.fireAsync(user);
        return type;
    }
    
    public void sendUserNotification(@Observes User user){
        System.out.println("sendUserNotification: " + user);
    }
    
    public void sendUserNotificationAsync(@ObservesAsync User user){
        System.out.println("sendUserNotificationAsync: " + user);
    }    
    
}
