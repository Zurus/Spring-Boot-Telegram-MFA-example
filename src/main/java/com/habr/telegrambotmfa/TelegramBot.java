package com.habr.telegrambotmfa;

import com.habr.telegrambotmfa.botCommands.ConnectAccountCommand;
import com.habr.telegrambotmfa.botCommands.MfaCommand;
import com.habr.telegrambotmfa.config.SettingsSource;
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
public class TelegramBot extends TelegramLongPollingCommandBot {
    private MfaCommand mfaCommand;
    private String botUsername;
    private String botToken;
    private SettingsSource settingsSource;

    public TelegramBot(ConnectAccountCommand connectAccountCommand, @Lazy MfaCommand mfaCommand, SettingsSource settingsSource) throws TelegramApiException {
        super(ApiContext.getInstance(DefaultBotOptions.class), false);
        this.settingsSource = settingsSource;
        this.botToken = this.settingsSource.getBotToken();//env.getRequiredProperty("telegram.bot.token");
        this.mfaCommand = mfaCommand;
        this.botUsername = getMe().getUserName();

        register(connectAccountCommand);
    }

    @PostConstruct
    public void addBot() throws TelegramApiRequestException {
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
