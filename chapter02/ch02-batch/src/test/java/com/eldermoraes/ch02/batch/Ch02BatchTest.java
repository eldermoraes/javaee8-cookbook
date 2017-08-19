/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eldermoraes.ch02.batch;

import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eldermoraes
 */
public class Ch02BatchTest {
    
    private EJBContainer ejbContainer;
    
    @EJB
    private UserBean userBean;    
    
    public Ch02BatchTest() {
    }
    
    @Before
    public void setUp() throws NamingException {
        Properties p = new Properties();
        p.put("userDb", "new://Resource?type=DataSource");
        p.put("userDb.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("userDb.JdbcUrl", "jdbc:hsqldb:mem:userdatabase");

        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);        
    }
    
    @After
    public void tearDown() {
        ejbContainer.close();
    }
    
    @Test
    public void runJob(){
        userBean.run();
        assertEquals(10, userBean.get().size());
    }
    
}
