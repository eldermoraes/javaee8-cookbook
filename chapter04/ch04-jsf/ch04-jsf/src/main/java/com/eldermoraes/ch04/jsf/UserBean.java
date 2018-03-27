package com.eldermoraes.ch04.jsf;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
 
@Named
@SessionScoped
public class UserBean implements Serializable{

    public Long getTimestamp(){
        return new Date().getTime();
    }
	
}