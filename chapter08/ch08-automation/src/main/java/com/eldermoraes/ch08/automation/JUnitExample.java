package com.eldermoraes.ch08.automation;

import javax.validation.constraints.Size;

/**
 *
 * @author eldermoraes
 */
public class JUnitExample {
    
    @Size (min = 6, max = 10,message = "Name should be between 6 and 10 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
