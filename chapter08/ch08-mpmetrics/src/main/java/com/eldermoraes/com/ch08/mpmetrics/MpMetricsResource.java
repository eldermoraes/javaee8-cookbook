/*
 * Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eldermoraes.com.ch08.mpmetrics;

import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 **
 * The message is returned as a JSON object.
 */
@Path("/ch08mpmetrics")
@RequestScoped
public class MpMetricsResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Timed(name = "getResourceTimed")
    @Path("/timed")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getResourceTimed() {
        String response = getResource();

        return JSON.createObjectBuilder()
                .add("message", response)
                .build();
    }

    @Metered(name = "getResourceMetered")
    @Path("/metered")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getResourceMetered() {
        String response = getResource();

        return JSON.createObjectBuilder()
                .add("message", response)
                .build();
    }

    private String getResource() {
        Client client = null;
        String response;

        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://eldermoraes.com/book");
            response = target.request()
                    .header("Content-Type", "application/json")
                    .get(String.class);
        } finally {
            if (client != null) {
                client.close();
            }
        }

        return response;
    }
}
