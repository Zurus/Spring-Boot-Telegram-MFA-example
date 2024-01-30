package com.habr.telegrambotmfa.settings;

import com.habr.telegrambotmfa.services.SettingsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class SettingsSourceProdImpl implements SettingsSource {

    private final SettingsService settingsService;

    @Override
    public String getValueByKey(String key) {
        log.info("Поиск настроек из БД key = " + key);
        return settingsService.getSettingByKey(key);
    }
}
