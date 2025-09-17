package com.kedu.endpoints;

import com.kedu.config.WebSocketConfigurator;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat", configurator = WebSocketConfigurator.class)
public class ChatEndpoint {

    public static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
    private HttpSession hSession;

    @OnOpen
    public void onConnection(Session session, EndpointConfig config) {
        System.out.println("Socket Connected");
        clients.add(session);
        this.hSession = (HttpSession) config.getUserProperties().get("hSession");
    }

    @OnMessage
    public void onMessage(String message) {
        synchronized (clients) {
            for (Session client : clients) {
                try {
                    client.getBasicRemote().sendText(this.hSession.getAttribute("loginId") + "<br>" + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        clients.remove(session);
        t.printStackTrace();
    }
}
