package com.habr.telegrambotmfa.exceptions;

import org.springframework.security.core.AuthenticationException;

public class RequireTelegramMfaException extends AuthenticationException {
    public RequireTelegramMfaException(String msg) {
        super(msg);
    }
}
