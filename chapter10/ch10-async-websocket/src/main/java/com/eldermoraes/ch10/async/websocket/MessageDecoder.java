package com.eldermoraes.ch10.async.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author eldermoraes
 */
public class MessageDecoder implements Decoder.Text<String> {

    @Override
    public String decode(String message) throws DecodeException {
        return message;
    }

    @Override
    public boolean willDecode(String message) {
        return true;
    }

    @Override
    public void init(EndpointConfig ec) {
        
    }

    @Override
    public void destroy() {
        
    }
    
}
