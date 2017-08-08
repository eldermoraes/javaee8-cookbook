package com.eldermoraes.ch01.jaxrs;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class GlobalConfig {

    public static final URI BASE_URI = URI.create("http://localhost:8080/");
    public static final String ROOT_PATH = "server-sent-events";

    public static void main(String[] args) {
        try {
            System.out.println("\"JAX-RS 2.1 Server-Sent Events\" Jersey Example App");

            final ResourceConfig resourceConfig = new ResourceConfig(SseResource.class);

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
            server.start();

            System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
                    BASE_URI, ROOT_PATH));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
