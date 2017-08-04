package com.eldermoraes.ch01.cdi;

/**
 *
 * @author eldermoraes
 */
public class ExampleEvent {
    
    private final String value;
    
    public ExampleEvent(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
