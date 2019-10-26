/*
 * Copyright 2019 eldermoraes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eldermoraes.ch01.jnosql.graph;

import javax.enterprise.context.ApplicationScoped;
import org.apache.tinkerpop.gremlin.structure.Graph;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.jnosql.artemis.graph.GraphTemplate;

/**
 *
 * @author eldermoraes
 */
@ApplicationScoped
public class DeveloperBean {

    @Inject
    private GraphTemplate graph;

    @Inject
    private Graph thinkerpop;

    @Inject
    private DeveloperService service;

    private final Jsonb jsonbBuilder = JsonbBuilder.create();

    public String getBuddiesByTechnology(String technology) {
        return jsonbBuilder.toJson(service.findByTechnology(technology));
    }
}
