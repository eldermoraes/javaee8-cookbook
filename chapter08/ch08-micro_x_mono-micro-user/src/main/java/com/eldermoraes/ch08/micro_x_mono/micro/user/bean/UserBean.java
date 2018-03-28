package com.eldermoraes.ch08.micro_x_mono.micro.user.bean;

import com.eldermoraes.ch08.micro_x_mono.micro.user.entity.User;
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
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public void add(User user) {
        em.persist(user);
    }

    public void remove(User user) {
        em.remove(user);
    }

    public void update(User user) {
        em.merge(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> get() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> pet = cq.from(User.class);
        cq.select(pet);
        TypedQuery<User> q = em.createQuery(cq);
        return q.getResultList();
    }
    
}
