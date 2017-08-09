package com.eldermoraes.ch01.security;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author eldermoraes
 */
@Stateful
public class UserBean {
    
    @PersistenceContext(unitName = "ch01-security-pu", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    @RolesAllowed({Roles.ADMIN, Roles.OPERATOR})
    public void add(User user){
        em.persist(user);
    }
    
    @RolesAllowed({Roles.ADMIN})
    public void remove(User user){
        em.remove(user);
    }
    
    @RolesAllowed({Roles.ADMIN})
    public void update(User user){
        em.merge(user);
    }
    
    @PermitAll
    public List<User> get(){
        Query q = em.createQuery("SELECT u FROM User as u ");
        return q.getResultList();
    }
    
}
