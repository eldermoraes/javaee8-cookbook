package com.eldermoraes.ch10.completable.future;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {

    public User getUser() {
        long id = new Date().getTime();
        try {
            TimeUnit.SECONDS.sleep(5);
            return new User(id, "User " + id);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            return new User(id, "Error " + id);
        }
    }

}
