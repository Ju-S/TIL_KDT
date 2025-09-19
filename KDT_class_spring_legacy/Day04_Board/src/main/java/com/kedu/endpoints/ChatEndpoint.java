package com.kedu.endpoints;

import com.google.gson.Gson;
import com.kedu.config.SpringProvider;
import com.kedu.config.WebSocketConfigurator;
import com.kedu.dto.ChatDTO;
import com.kedu.services.ChatService;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.sql.Timestamp;
import java.util.*;

// STOMP <-- WebSocket 통신의 Text 통신만 특화하여, 구독 시스템과 결합한 형태의 라이브러리
// SockJS <-- WebSocket을 지원하지 않는 브라우저나, WebSocket 프로토콜을 차단하는 네트워크에서 대안으로 사용되는 라이브러리

@ServerEndpoint(value = "/chat", configurator = WebSocketConfigurator.class)
public class ChatEndpoint {
    public static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
    //    private static final EvictingQueue<MessageDTO> history = EvictingQueue.create(300);
    private static final Gson gson = new Gson();
    private final ChatService chatService = SpringProvider.ctx.getBean(ChatService.class);
    private HttpSession hSession;

    @OnOpen
    public void onConnection(Session session, EndpointConfig config) {
        try {
            System.out.println("Socket Connected");
            clients.add(session);
            this.hSession = (HttpSession) config.getUserProperties().get("hSession");

            Map<String, Object> historyList = new HashMap<>();
            historyList.put("type", "history");
            historyList.put("contents", chatService.getChatHistory());

            session.getBasicRemote().sendText(gson.toJson(historyList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        ChatDTO dto = ChatDTO.builder()
                .sender((String) this.hSession.getAttribute("loginId"))
                .message(message)
                .sendDate(new Timestamp(System.currentTimeMillis()))
                .build();

        chatService.insert(dto);

        synchronized (clients) {
            for (Session client : clients) {
                try {
                    client.getBasicRemote().sendText(gson.toJson(dto));
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
