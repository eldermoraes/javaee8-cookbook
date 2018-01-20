package com.eldermoraes.ch08.micro_x_mono.micro.user.entity.service;

import com.kumuluz.ee.discovery.annotations.RegisterService;
import javax.ws.rs.core.Application;

/**
 *
 * @author eldermoraes
 */
@RegisterService(value = "user-service", environment = "dev", version = "1.0.0")
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

}
