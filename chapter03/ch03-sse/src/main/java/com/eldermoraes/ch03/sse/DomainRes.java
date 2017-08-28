package com.eldermoraes.ch03.sse;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author eldermoraes
 */
@Path("domainres")
public class DomainRes {

    private static final Map<Integer, MyEvent> EVENTS = new ConcurrentHashMap<>();

    @Path("start")
    @POST
    public Response start(@DefaultValue("0") @QueryParam("testSources") int testSources, @Context Sse sse) {

        final MyEvent process = new MyEvent(testSources, sse);

        EVENTS.put(process.getId(), process);
        Executors.newSingleThreadExecutor().execute(process);

        final URI uri = UriBuilder.fromResource(DomainRes.class).path("process/{id}").build(process.getId());
        return Response.created(uri).build();
    }

    @Path("process/{id}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @GET
    public void process(@PathParam("id") int id,
                    @DefaultValue("false") @QueryParam("testSource") boolean testSource,
                    @Context SseEventSink sseEventSink) {
        final MyEvent process = EVENTS.get(id);

        if (process != null) {
            if (testSource) {
                process.flush();
            }
            process.getSseBroadcaster().register(sseEventSink);
        } else {
            throw new NotFoundException();
        }
    }

    static class MyEvent implements Runnable {

        private final int id;
        private final CountDownLatch countDownLatch;
        private final SseBroadcaster sseBroadcaster;
        private final Sse sse;
        private static final AtomicInteger COUNT = new AtomicInteger(0);

        MyEvent(int testSources, Sse sse) {
            this.sse = sse;
            this.sseBroadcaster = sse.newBroadcaster();
            id = COUNT.incrementAndGet();
            countDownLatch = testSources > 0 ? new CountDownLatch(testSources) : null;
        }

        int getId() {
            return id;
        }

        SseBroadcaster getSseBroadcaster() {
            return sseBroadcaster;
        }

        void flush() {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }

        @Override
        public void run() {
            try {
                if (countDownLatch != null) {
                    countDownLatch.await(10, TimeUnit.SECONDS);
                }

                sseBroadcaster.broadcast(sse.newEventBuilder().name("progress").data(String.class, "starting with " + id + " ...").build());
                sseBroadcaster.broadcast(sse.newEventBuilder().name("progress").data(String.class, "50%").build());
                sseBroadcaster.broadcast(sse.newEventBuilder().name("progress").data(String.class, "60%").build());
                sseBroadcaster.broadcast(sse.newEventBuilder().name("progress").data(String.class, "70%").build());
                sseBroadcaster.broadcast(sse.newEventBuilder().name("progress").data(String.class, "80%").build());
                sseBroadcaster.broadcast(sse.newEventBuilder().name("progress").data(String.class, "90%").build());                
                sseBroadcaster.broadcast(sse.newEventBuilder().name("progress").data(String.class, "done").build());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
