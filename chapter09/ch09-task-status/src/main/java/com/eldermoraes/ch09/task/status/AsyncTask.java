package com.eldermoraes.ch09.task.status;

import java.util.concurrent.Future;
import javax.enterprise.concurrent.ManagedExecutorService;

/**
 *
 * @author eldermoraes
 */

import java.util.concurrent.Callable;
import javax.enterprise.concurrent.ManagedTaskListener;

/**
 *
 * @author eldermoraes
 */
public class AsyncTask implements Callable<User>,ManagedTaskListener {
    
    @Override
    public User call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void taskSubmitted(Future<?> future, ManagedExecutorService mes, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void taskAborted(Future<?> future, ManagedExecutorService mes, Object o, Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void taskDone(Future<?> future, ManagedExecutorService mes, Object o, Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void taskStarting(Future<?> future, ManagedExecutorService mes, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
