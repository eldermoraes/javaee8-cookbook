/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {

    @PersistenceContext(unitName = "ch02-jpa-pu", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    public void add(User user){
        em.persist(user);
    }
    
    public void update(User user){
        em.merge(user);
    }
    
    public void remove(User user){
        em.remove(user);
    }
    
    public User findById(Long id){
        return em.find(User.class, id);
    }
}
