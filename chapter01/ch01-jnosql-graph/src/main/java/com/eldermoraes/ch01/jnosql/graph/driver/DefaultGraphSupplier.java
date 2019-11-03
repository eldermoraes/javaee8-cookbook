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
package com.eldermoraes.ch01.jnosql.graph.driver;

import com.steelbridgelabs.oss.neo4j.structure.Neo4JElementIdProvider;
import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.ConfigurationUnit;
import org.neo4j.driver.v1.Driver;

/**
 *
 * @author eldermoraes
 */
@ApplicationScoped
public class DefaultGraphSupplier implements GraphSupplier {

    private static final Neo4JElementIdProvider<?> VERTEX_ID_PROVIDER = new Neo4JNativeElementIdProvider();
    private static final Neo4JElementIdProvider<?> EDGE_PROVIDER = new Neo4JNativeElementIdProvider();

    @Inject
    @ConfigurationUnit
    private Instance<Driver> driver;

    @Override
    public Graph get() {
        Neo4JGraph graph = new Neo4JGraph(driver.get(), VERTEX_ID_PROVIDER, EDGE_PROVIDER);
        graph.setProfilerEnabled(true);
        return graph;
    }

}
