package com.eldermoraes.ch02.datacache;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eldermoraes
 */
@Singleton
@Startup
public class UserCacheBean {

    protected Queue<User> cache = null;
    
    @PersistenceContext
    private EntityManager em;

    public UserCacheBean() {

    }

    protected void loadCache() {
        List<User> list = em.createNamedQuery("").getResultList();

        list.forEach((cidade) -> {
            cache.add(cidade);
        });
    }

    @Lock(LockType.READ)
    public List<User> read() {
        return cache.stream().collect(Collectors.toList());
    }

    @PostConstruct
    protected void init() {
        cache = new ConcurrentLinkedQueue<>();
        loadCache();
    }

}
