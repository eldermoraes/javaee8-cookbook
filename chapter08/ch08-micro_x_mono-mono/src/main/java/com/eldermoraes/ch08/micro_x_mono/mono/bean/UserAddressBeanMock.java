package com.eldermoraes.ch08.micro_x_mono.mono.bean;

import com.eldermoraes.ch08.micro_x_mono.mono.entity.UserAddress;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class UserAddressBeanMock {

    @PersistenceContext
    private EntityManager em;

    private static final List<UserAddress> ADDRESS_LIST = new ArrayList<>();

    public void add(UserAddress address) {
        ADDRESS_LIST.add(address);
    }

    public void remove(UserAddress address) {
        ADDRESS_LIST.remove(address);
    }

    public void update(UserAddress address) {
        for (UserAddress u: ADDRESS_LIST) {
            if (u.getId().equals(address.getId())) {
                ADDRESS_LIST.remove(address);
                break;
            }
        }
        ADDRESS_LIST.add(address);
    }

    public UserAddress findById(Long id) {
        for (UserAddress u : ADDRESS_LIST) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public List<UserAddress> get() {
        return ADDRESS_LIST;
    }
}
