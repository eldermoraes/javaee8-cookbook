/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.ejb.concurrency;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

/**
 *
 * @author eldermoraes
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserSelfManagedBean {

    private int userCount;

    public synchronized int getUserCount() {
        return userCount;
    }
    
    public void addUser(){
        userCount++;
    }
}
