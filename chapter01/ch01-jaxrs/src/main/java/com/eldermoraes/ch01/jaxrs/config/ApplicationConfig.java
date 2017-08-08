package com.eldermoraes.ch01.jaxrs.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author eldermoraes
 */
@javax.ws.rs.ApplicationPath("jaxrs")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.eldermoraes.ch01.jaxrs.Server.class);
    }
    
}
