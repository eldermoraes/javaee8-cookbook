/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.jta1;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author eldermoraes
 */
@Stateful
public class UserBean {

    @PersistenceContext(unitName = "ch02-jta-pu", type = PersistenceContextType.EXTENDED)
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
