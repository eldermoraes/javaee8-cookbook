package com.eldermoraes.ch09.async.transaction;

import java.util.concurrent.Callable;
import javax.enterprise.inject.spi.CDI;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author eldermoraes
 */
public class AsyncTask implements Callable<User> {

    private UserTransaction userTransaction;
    private UserBean userBean;

    @Override
    public User call() throws Exception {
        performLookups();
        try {
            userTransaction.begin();
            User user = userBean.getUser();
            userTransaction.commit();
            return user;
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            userTransaction.rollback();
            return null;
        }
    }

    private void performLookups() throws NamingException{
        userBean = CDI.current().select(UserBean.class).get();
        userTransaction = CDI.current().select(UserTransaction.class).get();
    }
    
}
