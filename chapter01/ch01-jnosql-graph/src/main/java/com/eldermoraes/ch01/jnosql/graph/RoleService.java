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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.artemis.graph.GraphTemplate;

/**
 *
 * @author eldermoraes
 */
@Path("/role")
@ApplicationScoped
public class RoleService {
    
    @Inject
    private GraphTemplate graphTemplate;
    
    @Inject
    @Database(DatabaseType.GRAPH)
    private RoleRepository roleRepository;
    
    @Inject
    private RoleBean roleBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> findByName(String name){
        Stream<Role> roles = graphTemplate.getTraversalVertex()
                .hasLabel(Role.class)
                .has("name", name)
                .orderBy("name")
                .asc()
                .stream();
        
        return roles.collect(Collectors.toList());
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createManager(String manager, String managed){
        Role managerRole = roleRepository.findByName(manager).orElse(roleBean.create(manager));
        Role managedRole = roleRepository.findByName(managed).orElse(roleBean.create(managed));
        
        roleBean.createManager(managerRole, managedRole);
        
        return Response.status(Response.Status.CREATED).build();
    }
}
