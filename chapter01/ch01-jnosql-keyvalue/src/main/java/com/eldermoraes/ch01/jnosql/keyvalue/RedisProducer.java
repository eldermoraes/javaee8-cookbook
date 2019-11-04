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
package com.eldermoraes.ch01.jnosql.keyvalue;

import jakarta.nosql.keyvalue.BucketManager;
import org.eclipse.jnosql.diana.redis.keyvalue.Counter;
import org.eclipse.jnosql.diana.redis.keyvalue.RedisBucketManagerFactory;
import org.eclipse.jnosql.diana.redis.keyvalue.RedisConfiguration;
import org.eclipse.jnosql.diana.redis.keyvalue.SortedSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author eldermoraes
 */
@ApplicationScoped
public class RedisProducer {

    private static final String BUCKET = "gods";

    private RedisConfiguration configuration;

    private RedisBucketManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new RedisConfiguration();
        managerFactory = configuration.get();
    }

    @Produces
    public BucketManager getManager() {
        return managerFactory.getBucketManager(BUCKET);
    }

    @Produces
    public List<String> getList() {
        return managerFactory.getList("list", String.class);
    }

    @Produces
    public Set<String> getSet() {
        return managerFactory.getSet("set", String.class);
    }

    @Produces
    public Queue<String> getQueue() {
        return managerFactory.getQueue("queue", String.class);
    }

    @Produces
    public Map<String, String> getMap() {
        return managerFactory.getMap("map", String.class, String.class);
    }

    @Produces
    public Counter getCounter() {
        return managerFactory.getCounter("counter");
    }

    @Produces
    public SortedSet getSortedSet() {
        return managerFactory.getSortedSet("good_score");
    }

}
