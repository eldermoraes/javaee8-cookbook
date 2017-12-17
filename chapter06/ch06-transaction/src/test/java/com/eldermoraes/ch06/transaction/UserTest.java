package com.eldermoraes.ch06.transaction;

import javax.ejb.EJB;
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
public class UserTest {
    
    private EJBContainer ejbContainer;
    
    @EJB
    private UserBean userBean;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() throws NamingException {
        ejbContainer = EJBContainer.createEJBContainer();
        ejbContainer.getContext().bind("inject", this);        
    }
    
    @After
    public void tearDown() {
        ejbContainer.close();
    }
    
    @Test
    public void test(){
        userBean.add(1);
        userBean.add(2);
        userBean.add(3);
        userBean.remove(2);
        int size = userBean.getActions().size();
        userBean.logout();
        Assert.assertEquals(2, size); 
    }
    
}
