package com.habr.telegrambotmfa.services;

import com.habr.telegrambotmfa.repositories.SettingsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

@Service
@AllArgsConstructor
@Slf4j
public class SettingsService {
    private final SettingsRepository settingsRepository;

    public String getSettingByKey(String key) {
        log.info("load setting {} from db", key);
        return settingsRepository.getSettingsByKey(key)
                .orElseThrow(() -> new NotFoundException("Settings with key " + key + " not found!")).getValue();
    }
}
