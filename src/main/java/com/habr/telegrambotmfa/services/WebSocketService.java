package com.habr.telegrambotmfa.services;

import com.habr.telegrambotmfa.login.AuthenticationInfo;
import com.habr.telegrambotmfa.repositories.WebSocketSessionStorage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebSocketService {
    private SimpMessagingTemplate simpMessagingTemplate;
    private WebSocketSessionStorage sessionStorage;

    public void sendLoginStatus(AuthenticationInfo info, String csrf) {
        String sessionId = sessionStorage.getSessionId(csrf);
        if (info.isSuccess()) {
            sessionStorage.removeSession(csrf);
        }

        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);

        simpMessagingTemplate.convertAndSendToUser(sessionId, "/queue/login", info, headerAccessor.getMessageHeaders());
    }
}
