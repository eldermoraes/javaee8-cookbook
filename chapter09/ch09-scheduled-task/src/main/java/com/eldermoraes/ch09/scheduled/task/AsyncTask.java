package com.eldermoraes.ch09.scheduled.task;

import com.eldermoraes.ch09.scheduled.task.remote.UserBean;
import java.util.concurrent.Callable;
import javax.enterprise.inject.spi.CDI;

/**
 *
 * @author eldermoraes
 */
public class AsyncTask implements Callable<User> {
    
    private final UserBean userBean = CDI.current().select(UserBean.class).get();

    @Override
    public User call() throws Exception {
        return userBean.getUser();
    }

}
