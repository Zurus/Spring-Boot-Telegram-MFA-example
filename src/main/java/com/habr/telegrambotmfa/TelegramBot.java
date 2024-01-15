package com.habr.telegrambotmfa;

import com.habr.telegrambotmfa.botCommands.ConnectAccountCommand;
import com.habr.telegrambotmfa.botCommands.MfaCommand;
import com.habr.telegrambotmfa.settings.SettingsSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
//https://core.telegram.org/bots/api
//https://tlgrm.ru/docs/bots
//https://javarush.ru/groups/posts/504-sozdanie-telegram-bota-na-java-ot-idei-do-deploja
//https://habr.com/ru/post/528694/
//https://toolkas.blogspot.com/2018/10/telegram-bot-java.html
//https://tproger.ru/articles/sozdajom-bota-v-telegram-dlja-upravlenija-platnymi-podpiskami-na-kanal/
//https://h.amazingsoftworks.com/ru/post/571762/
//https://javarush.ru/groups/posts/2959-sozdaem-telegram-bota-s-ispoljhzovaniem-spring-boot
public class TelegramBot extends TelegramLongPollingCommandBot {
    private MfaCommand mfaCommand;
    private String botUsername;
    private String botToken;
    private SettingsSource settingsSource;

    public TelegramBot(ConnectAccountCommand connectAccountCommand, @Lazy MfaCommand mfaCommand, SettingsSource settingsSource) {
        super(ApiContext.getInstance(DefaultBotOptions.class), false);
        this.settingsSource = settingsSource;
        this.mfaCommand = mfaCommand;
        register(connectAccountCommand);
    }

    //@PostConstruct
    public void addBot() throws TelegramApiRequestException {
        this.botToken = settingsSource.getBotToken();
        this.botUsername = settingsSource.getBotName();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(this);
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            mfaCommand.onCallbackQuery(update.getCallbackQuery());
        }
    }

    public String getUsernameByTelegramChatId(long telegramChatId) throws TelegramApiException {
        GetChat getChat = new GetChat(telegramChatId);
        return execute(getChat).getUserName();
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
