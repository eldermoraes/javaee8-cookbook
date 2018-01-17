package com.eldermoraes.ch08.micro_x_mono.mono.bean;

import com.eldermoraes.ch08.micro_x_mono.mono.entity.UserAddress;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserAddressBean {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    public void add(UserAddress address){
        em.persist(address);
    }
    
    public void remove(UserAddress address){
        em.remove(address);
    }
    
    public void update(UserAddress address){
        em.merge(address);
    }
    
    public UserAddress findById(Long id){
        return em.find(UserAddress.class, id);
    }
}
