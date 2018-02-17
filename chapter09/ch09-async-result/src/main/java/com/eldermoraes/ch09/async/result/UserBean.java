package com.eldermoraes.ch09.async.result;

import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBean {

    public User getSlowUser() {
        try {
            TimeUnit.SECONDS.sleep(5);
            return new User(1L, "Elder Moraes");
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
