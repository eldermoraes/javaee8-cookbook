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

import jakarta.nosql.mapping.Repository;

import java.util.Optional;

/**
 *
 * @author eldermoraes
 */
public interface RoleRepository extends Repository<Role, String> {
    Optional<Role> findByName(String name);
}
