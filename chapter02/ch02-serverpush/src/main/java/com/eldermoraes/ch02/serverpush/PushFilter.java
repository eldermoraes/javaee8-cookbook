/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.serverpush;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;

/**
 *
 * @author eldermoraes
 */
@WebFilter(filterName = "PushFilter", urlPatterns = {"/*"})
public class PushFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpReq = (HttpServletRequest)request;
        PushBuilder builder =  httpReq.newPushBuilder();
        
        if (builder != null){
            builder
                .path("resources/javaee-logo.png")
                .path("resources/style.css")
                .path("resources/functions.js")
                .push();            
            System.out.println("Resources pushed");
        }

        chain.doFilter(request, response);
        
    }

    
}
