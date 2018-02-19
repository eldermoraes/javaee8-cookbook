package com.eldermoraes.ch09.proxy.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ContextService;
import javax.enterprise.concurrent.ManagedThreadFactory;

/**
 *
 * @author eldermoraes
 */
@Singleton
public class ExecutorProxy {

    @Resource(name = "LocalManagedThreadFactory")
    private ManagedThreadFactory factory;

    @Resource(name = "LocalContextService")
    private ContextService context;

    private ExecutorService executor;

    @PostConstruct
    public void init(){
        executor = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), factory);
    }
    
    public Future<User> submit(Callable<User> task){
        Callable<User> ctxProxy = context.createContextualProxy(task, Callable.class);
        return executor.submit(ctxProxy);
    }
}
