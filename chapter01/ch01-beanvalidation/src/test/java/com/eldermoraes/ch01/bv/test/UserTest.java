package com.eldermoraes.ch01.bv.test;

import com.eldermoraes.ch01.bv.User;
import static java.util.Arrays.asList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author eldermoraes
 */
public class UserTest {
    
    private static Validator validator;
    
    @BeforeClass
    public static void setUpClass() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    @Test
    public void validUser() {
        User user = new User(
                "elder", 
                "elder@eldermoraes.com", 
                asList(1,2));
        
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertTrue(cv.isEmpty());
    }
    
    @Test
    public void invalidName() {
        User user = new User(
                "", 
                "elder@eldermoraes.com", 
                asList(1,2));
        
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidEmail() {
        User user = new User(
                "elder", 
                "elder-eldermoraes_com", 
                asList(1,2));
        
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }    
    
    @Test
    public void invalidId() {
        User user = new User(
                "elder", 
                "elder@eldermoraes.com", 
                asList(-1,-2,1,2));
        
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(2, cv.size());
    }    
    
}
