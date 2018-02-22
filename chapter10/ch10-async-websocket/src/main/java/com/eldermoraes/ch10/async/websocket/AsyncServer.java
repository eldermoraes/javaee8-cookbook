package com.eldermoraes.ch10.async.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author eldermoraes
 */
@Singleton
@ServerEndpoint(value = "/asyncServer")
public class AsyncServer {
    
    private final List<Session> peers = Collections.synchronizedList(new ArrayList<>());
    
    @OnOpen
    public void onOpen(Session peer){
        peers.add(peer);
    }
    
    @OnClose
    public void onClose(Session peer){
        peers.remove(peer);
    }
    
    @OnError
    public void onError(Throwable t){
        System.err.println(t.getMessage());
    }
    
    @OnMessage
    public void onMessage(String message, Session peer){
        peers.stream().filter((p) -> (p.isOpen())).forEachOrdered((p) -> {
            p.getAsyncRemote().sendText(message + " - Total peers: " + peers.size());
        });
    }
}
