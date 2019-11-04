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
import org.eclipse.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author eldermoraes
 */
@ApplicationScoped
public class RoleBean {
    
    @Inject
    private GraphTemplate graphTemplate;
    
    @Inject
    private RoleRepository roleRepository;

    public Role create(String name){
        Role role = new Role(name);
        graphTemplate.insert(role);       
        return roleRepository.findByName(name).get();
    }
    
    public Role createCEO(){
        Role role = new Role("CEO");
        graphTemplate.insert(role);       
        return roleRepository.findByName("CEO").get();
    }    
    
    public void createManager(Role manager, Role managed){
        graphTemplate.edge(manager, "manage", managed);
    }
 
    public List<Role> findByName(String name){
        Stream<Role> roles = graphTemplate.getTraversalVertex()
                .hasLabel(Role.class)
                .has("name", name)
                .orderBy("name")
                .asc()
                .getResult();
        
        return roles.collect(Collectors.toList());        
    }
}
