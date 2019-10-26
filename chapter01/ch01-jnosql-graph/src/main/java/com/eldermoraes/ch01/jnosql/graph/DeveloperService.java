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

import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jnosql.artemis.graph.GraphTemplate;

/**
 *
 * @author eldermoraes
 */
@ApplicationScoped
public class DeveloperService {

    @Inject
    private GraphTemplate graphTemplate;

    public List<Developer> findByTechnology(String technology) throws NullPointerException {
        requireNonNull(technology, "technology is required");

        Stream<Developer> devs = graphTemplate.getTraversalVertex()
                .hasLabel(Technology.class)
                .has("name", technology)
                .in(Edges.WORKS).orderBy("name").asc().stream();

        return devs.collect(Collectors.toList());
    }

    public void work(Developer developer, Technology technology) {
        requireNonNull(developer, "developer is required");
        requireNonNull(technology, "technology is required");

        graphTemplate.edge(developer, Edges.WORKS, technology);
    }

    public Register getRegister() {
        return new DefaultRegister(graphTemplate);
    }

}
