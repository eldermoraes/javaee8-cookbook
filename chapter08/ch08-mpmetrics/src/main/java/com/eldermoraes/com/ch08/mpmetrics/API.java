package com.eldermoraes.com.ch08.mpmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@ApplicationScoped
public class API {

    public String getResource() {
        Client client = null;
        String response;

        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://eldermoraes.com/book");
            response = target.request()
                    .header("Content-Type", "application/json")
                    .get(String.class);
        } finally {
            if (client != null)
                client.close();
        }

        return response;
    }

}

