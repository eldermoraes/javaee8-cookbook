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

import static java.util.Locale.US;
import org.jnosql.artemis.EntityNotFoundException;
import org.jnosql.artemis.graph.GraphTemplate;

/**
 *
 * @author eldermoraes
 */
final class DefaultRegister implements Register, Register.RegisterWork {
 
    private final GraphTemplate graphTemplate;

    private Developer developer;
    
    private Technology technology;
    
    DefaultRegister(GraphTemplate graphTemplate) {
        this.graphTemplate = graphTemplate;
    }

    @Override
    public RegisterWork developer(DeveloperDTO dto) {
        developer = dto.toEntity();
        developer.setSalary(dto.getSalary());
        if (developer.isNew()) {
            graphTemplate.insert(developer);
        } else {
            graphTemplate.update(developer);
        }
        graphTemplate.getTransaction().commit();

        return this;
    }

    @Override
    public void works(String technology) {
         this.technology = graphTemplate.getTraversalVertex().hasLabel(Technology.class)
                .has("name", technology.toLowerCase(US)).<Technology>getSingleResult()
                .orElseThrow(
                        () -> new EntityNotFoundException("Technology does not found with the name: " + technology));

    }
}
