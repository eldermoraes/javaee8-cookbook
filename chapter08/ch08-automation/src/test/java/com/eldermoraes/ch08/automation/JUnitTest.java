package com.eldermoraes.ch08.automation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author eldermoraes
 */
public class JUnitTest {
    
    private static Validator VALIDATOR;
    
    @BeforeClass
    public static void setUpClass() {
        VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void smallName(){
        JUnitExample junit = new JUnitExample();
        
        junit.setName("Name");
        
        Set<ConstraintViolation<JUnitExample>> cv = VALIDATOR.validate(junit);
        assertFalse(cv.isEmpty());
    }
    
    @Test
    public void validName(){
        JUnitExample junit = new JUnitExample();
        
        junit.setName("Valid Name");
        
        Set<ConstraintViolation<JUnitExample>> cv = VALIDATOR.validate(junit);
        assertTrue(cv.isEmpty());
    }

    @Test
    public void invalidName(){
        JUnitExample junit = new JUnitExample();
        
        junit.setName("Invalid Name");
        
        Set<ConstraintViolation<JUnitExample>> cv = VALIDATOR.validate(junit);
        assertFalse(cv.isEmpty());
    }    
}
