package com.eldermoraes.ch01.jaxrs;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;

@Path("ss-events")
@Singleton
public class Server {

    @Resource
    private ManagedExecutorService executor;

    private final Sse sse;
    private SseEventSink sseSink;

    @Inject
    public Server(Sse sse) {
        this.sse = sse;
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getMessageQueue(@Context SseEventSink eventSink) {
        if (this.sseSink != null) {
            throw new IllegalStateException("Sink already instantiated.");
        }
        this.sseSink = eventSink;
    }

    @POST
    public void addMessage(final String message) throws IOException {
        if (sseSink == null) {
            throw new IllegalStateException("No sink available.");
        }
        sseSink.send(sse.newEvent("jax-rs 2.1"));
    }

    @DELETE
    public void close() throws IOException {
        if (sseSink != null) {
            sseSink.close();
            sseSink = null;
        }
    }

    @POST
    @Path("sendEvent/{id}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void sendEvent(@PathParam("id") String id,
                          @Context SseEventSink sink) {

        executor.submit(() -> {
            try {
                sink.send(sse.newEventBuilder().name("steps")
                                  .data(String.class, "sending event " + id + " ...").build());
                Thread.sleep(200);
                
                sink.send(sse.newEvent("step", "1"));
                Thread.sleep(200);
                
                sink.send(sse.newEvent("step", "2"));
                Thread.sleep(200);
                
                sink.send(sse.newEvent("step", "3"));
                Thread.sleep(200);
                
                sink.send(sse.newEvent("step", "Final"));
                sink.close();
                
            } catch (final InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
