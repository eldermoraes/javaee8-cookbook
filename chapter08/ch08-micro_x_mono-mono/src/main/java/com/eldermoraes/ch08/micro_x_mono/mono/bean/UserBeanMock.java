package com.eldermoraes.ch08.micro_x_mono.mono.bean;

import com.eldermoraes.ch08.micro_x_mono.mono.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserBeanMock {

    private static final List<User> USER_LIST = new ArrayList<>();

    public void add(User user) {
        Long id = new Date().getTime();
        User u = new User("John " + id, id + "@blah.com");
        u.setId(id);
        USER_LIST.add(u);
    }

    public void remove(User user) {
        USER_LIST.remove(user);
    }

    public void update(User user) {
        for (User u : USER_LIST) {
            if (u.getId().equals(user.getId())) {
                USER_LIST.remove(u);
                break;
            }
        }
        USER_LIST.add(user);
    }

    public User findById(Long id) {
        for (User u : USER_LIST) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public List<User> get() {
        return USER_LIST;
    }

}
