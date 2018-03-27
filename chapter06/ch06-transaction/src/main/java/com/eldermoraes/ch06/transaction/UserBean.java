package com.eldermoraes.ch06.transaction;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.BeforeCompletion;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;

/**
 *
 * @author eldermoraes
 */
@Stateful
@TransactionManagement
public class UserBean {
    
    private ArrayList<Integer> actions;
    
    @PostConstruct
    public void init(){
        actions = new ArrayList();
        System.out.println("UserBean initialized");
    }
    
    public void add(Integer action){
        actions.add(action);
        System.out.println(action + " added");
    }
    
    public void remove(Integer action){
        actions.remove(action);
        System.out.println(action + " removed");
    }
    
    public List getActions(){
        return actions;
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("UserBean will be destroyed");
    }
    
    @Remove
    public void logout(){
        System.out.println("User logout. Resources will be released.");
    }
    
    @AfterBegin
    public void transactionStarted(){
        System.out.println("Transaction started");
    }
    
    @BeforeCompletion
    public void willBeCommited(){
        System.out.println("Transaction will be commited");
    }
    
    @AfterCompletion
    public void afterCommit(boolean commited){
        System.out.println("Transaction commited? " + commited);
    }
    
}
