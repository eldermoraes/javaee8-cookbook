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
package com.eldermoraes.com.ch08.mpopenapi;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/")
@RequestScoped
@Tag(name = "Contact API service", description = "Methods for Contact management")
public class ContactResource {

    @GET
    @Path("/contacts")
    @APIResponse(responseCode = "200",
            description = "Contact list response",
            name = "ContactListResponse"
    )
    public Response getContacts() {
        return Response.ok("This should be a contact list").build();
    }

    @GET
    @Path("/contact/{id}")
    @APIResponse(responseCode = "200",
            description = "Single contact response",
            name = "SingleContactResponse"
    )
    @Parameter(name = "id",
            description = "Contact Id",
            required = true,
            allowEmptyValue = false
    )
    public Response getContactById(@PathParam("id") Long id) {
        return Response.ok("This is a single contact").build();
    }
}
