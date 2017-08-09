package com.eldermoraes.ch01.jsonp;

import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import org.glassfish.json.JsonPointerImpl;

/**
 *
 * @author eldermoraes
 */
public class JPointer {
    
    
    public static void main(String[] args) throws IOException{
        try (InputStream is = JPointer.class.getClassLoader().getResourceAsStream("user.json");
             JsonReader jr = Json.createReader(is)) {
            
            JsonStructure js = jr.read();
            JsonPointer jp = new JsonPointerImpl("/user/profile");
            JsonValue jv = jp.getValue(js);
            System.out.println("profile: " + jv);

        }
    }
}
