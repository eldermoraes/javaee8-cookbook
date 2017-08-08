package com.eldermoraes.ch01.jaxrs;

import javax.ws.rs.client.Entity;
import javax.ws.rs.sse.SseEventSource;

public class ClientConsumer {

    public static void main(String[] args) {
        consume();
    }

    private static void consume() {

        try (final SseEventSource sseSource =
                     SseEventSource
                             .target(ServerMock.WEB_TARGET)
                             .build()) {

            sseSource.register(System.out::println);
            sseSource.open();

            for (int counter=0; counter < 5; counter++) {
                System.out.println(" ");
                for (int innerCounter=0; innerCounter < 5; innerCounter++) {
                    ServerMock.WEB_TARGET.request().post(Entity.json("event " + innerCounter));
                }
                Thread.sleep(1000);
            }
            
            System.out.println("\nAll messages consumed");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
