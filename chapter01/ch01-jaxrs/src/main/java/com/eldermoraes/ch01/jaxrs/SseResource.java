package com.eldermoraes.ch01.jaxrs;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path(ServerMock.BASE_PATH)
public class SseResource {

    private static volatile SseEventSink SINK = null;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getMessageQueue(@Context SseEventSink sink) {
        SseResource.SINK = sink;
    }

    @POST
    public void addMessage(final String message, @Context Sse sse) throws IOException {
        if (SINK != null) {
            SINK.send(sse.newEventBuilder()
                    .name("sse-message")
                    .id(String.valueOf(System.currentTimeMillis()))
                    .data(String.class, message)
                    .comment("")
                    .build());
        }
    }
}
