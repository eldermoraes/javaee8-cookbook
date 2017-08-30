package com.eldermoraes.ch03.sse;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.SseEventSource;

/**
 *
 * @author eldermoraes
 */
@ViewScoped
@Named
public class EventBean implements Serializable {

    @NotNull
    @Positive
    private Integer countClient;

    public void sendEvents() throws URISyntaxException, InterruptedException {
        WebTarget target = ClientBuilder.newClient().target(URI.create("http://localhost:8080/ch03-sse/"));
        Response response = target.path("webresources/server-event/start")
                .queryParam("testSources", countClient)
                .request()
                .post(Entity.json(""), Response.class);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Start status: " + response.getStatusInfo() + " [" + response.getStatus() + "]"));

        final Map<Integer, String> messageCounts = new ConcurrentHashMap<>(countClient);
        final CountDownLatch doneLatch = new CountDownLatch(countClient);
        final SseEventSource[] sources = new SseEventSource[countClient];

        final String processUriString = target.getUri().relativize(response.getLocation()).toString();
        final WebTarget sseTarget = target.path(processUriString).queryParam("testSource", "true");

        final StringBuilder messageBroadcast = new StringBuilder();

        for (int i = 0; i < countClient; i++) {
            String eventMessage = "Timestamp is " + System.currentTimeMillis();
            final int id = i;
            sources[id] = SseEventSource.target(sseTarget).build();
            sources[id].register((event) -> {
                final String message = event.readData(String.class);

                messageBroadcast.append(message).append("        ");

                if ("done".equals(message)) {
                    messageCounts.put(id, eventMessage);
                    doneLatch.countDown();
                }
            });
            sources[i].open();
        }

        doneLatch.await(10, TimeUnit.SECONDS);

        for (SseEventSource source : sources) {
            source.close();
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(messageBroadcast.toString()));

        for (int i = 0; i < countClient; i++) {
            final String count = messageCounts.get(i);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Thread " + i + " ended. " + count));
        }
    }

    public Integer getCountClient() {
        return countClient;
    }

    public void setCountClient(Integer countClient) {
        this.countClient = countClient;
    }

}
