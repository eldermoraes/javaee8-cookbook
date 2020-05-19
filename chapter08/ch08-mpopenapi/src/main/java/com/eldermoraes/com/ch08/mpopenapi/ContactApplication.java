/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.eldermoraes.com.ch08.mpopenapi;

import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.helidon.common.CollectionsHelper;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationScoped
@ApplicationPath("/ch08mpopenapi")
@OpenAPIDefinition(info = @Info(
        title = "Chapter 08 MicroProfile OpenAPI",
        version = "1.0.0",
        contact = @Contact(
                name = "Elder Moraes",
                email = "elder@eldermoraes.com",
                url = "https://eldermoraes.com")
),
        servers = {
            @Server(url = "/ch08mpopenapi", description = "localhost")
        }
)
public class ContactApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return CollectionsHelper.setOf(ContactResource.class);
    }
}
