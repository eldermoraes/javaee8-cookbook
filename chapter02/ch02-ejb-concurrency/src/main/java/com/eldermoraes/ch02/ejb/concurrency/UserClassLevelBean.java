/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.ejb.concurrency;

import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/**
 *
 * @author eldermoraes
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER) //redundant
@Lock(LockType.READ)
@AccessTimeout(value = 10000)
public class UserClassLevelBean {

    private int userCount;

    public int getUserCount() {
        return userCount;
    }
    
    public void addUser(){
        userCount++;
    }

}
