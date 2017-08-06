package com.eldermoraes.beanvalidation.test;

import com.eldermoraes.beanvalidation.User;
import java.util.Arrays;
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
    
    private static Validator VALIDATOR;
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    @Test
    public void validUser() {
        User user = new User(
                "elder", 
                "elder@eldermoraes.com", 
                Arrays.asList(new Integer[]{1,2}));
        
        Set<ConstraintViolation<User>> cv = VALIDATOR.validate(user);
        assertTrue(cv.isEmpty());
    }
    
    @Test
    public void invalidName() {
        User user = new User(
                "", 
                "elder@eldermoraes.com", 
                Arrays.asList(new Integer[]{1,2}));
        
        Set<ConstraintViolation<User>> cv = VALIDATOR.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidEmail() {
        User user = new User(
                "elder", 
                "elder-eldermoraes_com", 
                Arrays.asList(new Integer[]{1,2}));
        
        Set<ConstraintViolation<User>> cv = VALIDATOR.validate(user);
        assertEquals(1, cv.size());
    }    
    
    @Test
    public void invalidId() {
        User user = new User(
                "elder", 
                "elder@eldermoraes.com", 
                Arrays.asList(new Integer[]{-1,-2,1,2}));
        
        Set<ConstraintViolation<User>> cv = VALIDATOR.validate(user);
        assertEquals(2, cv.size());
    }    
    
}
