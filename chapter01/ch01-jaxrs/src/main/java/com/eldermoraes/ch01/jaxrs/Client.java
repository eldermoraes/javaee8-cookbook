package com.eldermoraes.ch01.jaxrs;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

/**
 *
 * @author eldermoraes
 */
public class Client {

    public void readFromServer(WebTarget target) {
        try (
            final SseEventSource sses = 
                SseEventSource.target(target).build()) {
                    //eventSource.subscribe(System.out::println);
                    sses.register(System.out::println);
                    sses.open();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    
                }
    }

}
