package com.eldermoraes.ch01.jaxrs;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

public class Client {

    public static final WebTarget WEB_TARGET = ClientBuilder.newClient().target("ss-events");

    public static void main(String[] args) {
        consume();
    }

    private static void consume() {

        try (final SseEventSource sseSource =
                     SseEventSource
                             .target(WEB_TARGET)
                             .build()) {

            sseSource.register(System.out::println);
            sseSource.open();

            for (int counter = 0; counter < 5; counter++) {
                WEB_TARGET.request().post(Entity.text("event: " + counter));
            }

            Thread.sleep(800); 
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
