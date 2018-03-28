package com.eldermoraes.ch08.micro_x_mono.micro.address.bean;

import com.eldermoraes.ch08.micro_x_mono.micro.address.entity.UserAddress;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserAddressBean {

    @PersistenceContext
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
    
    public List<UserAddress> get() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserAddress> cq = cb.createQuery(UserAddress.class);
        Root<UserAddress> pet = cq.from(UserAddress.class);
        cq.select(pet);
        TypedQuery<UserAddress> q = em.createQuery(cq);
        return q.getResultList();
    }    
}
