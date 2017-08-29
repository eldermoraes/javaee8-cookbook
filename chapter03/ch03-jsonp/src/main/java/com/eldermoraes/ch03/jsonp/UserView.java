package com.eldermoraes.ch03.jsonp;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

/**
 *
 * @author eldermoraes
 */

@ViewScoped
@Named
public class UserView implements Serializable{
    
    private static final JsonBuilderFactory BUILDERFACTORY = Json.createBuilderFactory(null);
    
    public void loadArray(){
        JsonArray array = BUILDERFACTORY.createArrayBuilder()
                .add(BUILDERFACTORY.createObjectBuilder()
                        .add("name", "User1")
                        .add("email", "user1@eldermoraes.com"))
                .add(BUILDERFACTORY.createObjectBuilder()
                        .add("name", "User2")
                        .add("email", "user2@eldermoraes.com"))
                .add(BUILDERFACTORY.createObjectBuilder()
                        .add("name", "User3")
                        .add("email", "user3@eldermoraes.com"))                
                .build();        
    }
    
    public void loadStructure(){
        JsonObject structure = BUILDERFACTORY.createObjectBuilder()
                .add("name", "User1")
                .add("email", "user1@eldermoraes.com")
                .add("profiles", BUILDERFACTORY.createArrayBuilder()
                        .add(BUILDERFACTORY.createObjectBuilder()
                                .add("id", "1")
                                .add("name", "Profile1"))
                        .add(BUILDERFACTORY.createObjectBuilder()
                                .add("id", "2")
                                .add("name", "Profiles")))
                .build();
        
    }
    
}
