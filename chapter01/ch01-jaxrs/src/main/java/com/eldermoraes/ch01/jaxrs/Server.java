package com.eldermoraes.ch01.jaxrs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author eldermoraes
 */
@Stateless
@Path("servers")
public class Server {

    @GET
    @Path("eventStream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void eventStream(@Context SseEventSink sseSink, @Context Sse sse) {
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try (SseEventSink sink = sseSink) {
                sseSink.send(sse.newEvent("eventA"));
                //sseSink.onNext(sse.newEvent("eventA"));

                sseSink.send(sse.newEvent("eventB"));
                //sseSink.onNext(sse.newEvent("eventB"));
                
                sseSink.send(sse.newEvent("eventC"));
                
                //sseSink.onNext(sse.newEvent("eventC"));

                sseSink.close();
                // handle exception
            }
        });
    }

}
