package com.eldermoraes.ch01.security;

import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author eldermoraes
 */
public class Ch01SecurityTest {

    private EJBContainer ejbContainer;

    @EJB(name = "AdminExecutor")
    private RoleExecutor.AdminExecutor adminExecutor;

    @EJB(name = "OperatorExecutor")
    private RoleExecutor.OperatorExecutor operatorExecutor;

    @EJB
    private UserBean userBean;

    public Ch01SecurityTest() {
    }

    @Before
    public void setUp() throws NamingException {
        Properties p = new Properties();
        p.put("userDb", "new://Resource?type=DataSource");
        p.put("userDb.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("userDb.JdbcUrl", "jdbc:hsqldb:mem:userdatabase");

        this.ejbContainer = EJBContainer.createEJBContainer(p);
        this.ejbContainer.getContext().bind("inject", this);
    }

    @After
    public void tearDown() {
        this.ejbContainer.close();
    }

    @Test
    public void asAdmin() throws Exception {
        adminExecutor.run(() -> {
            userBean.add(new User(1L, "user1", "user1@user.com"));
            userBean.add(new User(2L, "user2", "user2@user.com"));
            userBean.add(new User(3L, "user3", "user3@user.com"));
            userBean.add(new User(4L, "user4", "user4@user.com"));

            List<User> list = userBean.get();

            list.forEach((user) -> {
                userBean.remove(user);
            });

            Assert.assertEquals("userBean.get()", 0, userBean.get().size());

        });
    }

    @Test
    public void asOperator() throws Exception {
        operatorExecutor.run(() -> {
            userBean.add(new User(1L, "user1", "user1@user.com"));
            userBean.add(new User(2L, "user2", "user2@user.com"));
            userBean.add(new User(3L, "user3", "user3@user.com"));
            userBean.add(new User(4L, "user4", "user4@user.com"));

            List<User> list = userBean.get();

            list.forEach((user) -> {
                try {
                    userBean.remove(user);
                    Assert.fail("Operator was able to remove user " + user.getName());
                } catch (EJBAccessException e) {

                }
            });


            Assert.assertEquals("userBean.get()", 4, userBean.get().size());

        });
    }

    @Test
    public void asAnonymous() {
        try {
            userBean.add(new User(1L, "elder", "elder@eldermoraes.com"));
            Assert.fail("Anonymous user should not add users");
        } catch (EJBAccessException e) {

        }
        
        try {
            userBean.remove(new User(1L, "elder", "elder@eldermoraes.com"));
            Assert.fail("Anonymous user should not remove users");
        } catch (EJBAccessException e) {

        }

        try {
            userBean.get();
        } catch (EJBAccessException e) {
            Assert.fail("Everyone can list users");
        }        
    }
}
