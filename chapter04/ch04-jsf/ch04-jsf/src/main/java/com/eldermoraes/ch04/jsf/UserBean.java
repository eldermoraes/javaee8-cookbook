package com.eldermoraes.ch04.jsf;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
 
@Named
@RequestScoped
public class UserBean implements Serializable{

    public Long getTimestamp(){
        return new Date().getTime();
    }
	
}