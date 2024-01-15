package com.habr.telegrambotmfa.config;

import com.habr.telegrambotmfa.TelegramBot;
import com.habr.telegrambotmfa.repositories.SettingsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Component
@AllArgsConstructor
@Slf4j
public class RunAfterStartUp {

    private TelegramBot telegramBot;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartUp() throws TelegramApiRequestException {
        log.info("Initialization telegram bot");
        //telegramBot.addBot();
    }
}

