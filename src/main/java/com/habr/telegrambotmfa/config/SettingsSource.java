package com.habr.telegrambotmfa.config;

public interface SettingsSource {
     String BOT_NAME = "bot.name";
     String BOT_TOKEN = "bot.token";

    default String getBotName() {
        return getValueByKey(BOT_NAME);
    }

    default String getBotToken() {
        return getValueByKey(BOT_TOKEN);
    }

    String getValueByKey(String key);
}
