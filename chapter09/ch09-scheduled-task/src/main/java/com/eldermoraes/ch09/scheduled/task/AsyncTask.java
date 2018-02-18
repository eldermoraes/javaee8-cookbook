package com.eldermoraes.ch09.scheduled.task;

import com.eldermoraes.ch09.scheduled.task.remote.UserBean;
import java.util.concurrent.Callable;
import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class AsyncTask implements Callable<User> {

    @Override
    public User call() throws Exception {
        return new UserBean().getUser();
    }

}
