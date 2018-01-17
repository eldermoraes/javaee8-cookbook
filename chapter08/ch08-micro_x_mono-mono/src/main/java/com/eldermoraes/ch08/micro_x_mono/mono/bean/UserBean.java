package com.eldermoraes.ch08.micro_x_mono.mono.bean;

import com.eldermoraes.ch08.micro_x_mono.mono.entity.User;
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

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    public void add(User user){
        em.persist(user);
    }
    
    public void remove(User user){
        em.remove(user);
    }
    
    public void update(User user){
        em.merge(user);
    }
    
    public User findById(Long id){
        return em.find(User.class, id);
    }

}
