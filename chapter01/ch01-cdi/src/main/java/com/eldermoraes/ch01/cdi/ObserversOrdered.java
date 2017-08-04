package com.eldermoraes.ch01.cdi;

import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

/**
 *
 * @author eldermoraes
 */
public class ObserversOrdered {
    
    @Inject
    private Event<Ch01CdiEvent> event;
    
    public void fire(){
        event.fire(new Ch01CdiEvent());
    }
    
    /**
     *
     * @param event
     */
    public void thisOneBefore(@Observes @Priority(Interceptor.Priority.APPLICATION) Ch01CdiEvent event){
        
    }
    
    
    
}
