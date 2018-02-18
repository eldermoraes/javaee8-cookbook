package com.eldermoraes.ch09.async.transaction;

import java.util.concurrent.Callable;
import javax.naming.Context;
import javax.naming.InitialContext;
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
        Context ctx = new InitialContext();
        userTransaction = (UserTransaction) ctx.lookup("java:comp/UserTransaction");
        userBean = (UserBean) ctx.lookup("java:global/ch09-async-transaction/UserBean");
    }
    
}
