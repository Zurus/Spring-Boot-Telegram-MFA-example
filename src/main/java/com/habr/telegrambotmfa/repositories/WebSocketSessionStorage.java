package com.habr.telegrambotmfa.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class WebSocketSessionStorage  implements ApplicationListener<SessionConnectEvent> {
    private Map<String, String> storage = new HashMap<>();

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        log.info("login", event);
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();
        List<String> nativeHeader = sha.getNativeHeader("X-CSRF-TOKEN");
        if (nativeHeader != null && nativeHeader.size() != 0) {
            storage.put(nativeHeader.get(0), sessionId);
        }
    }

    public String getSessionId(String csrf) {
        return storage.get(csrf);
    }

    public void removeSession(String csrf) {
        storage.remove(csrf);
    }
}
