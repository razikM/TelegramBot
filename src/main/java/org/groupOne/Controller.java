package org.groupOne;


import java.util.Map;
import org.apache.log4j.Logger;
import org.groupOne.service.SendMessageBotService;
import org.groupOne.service.button.ButtonContainer;
import org.groupOne.service.button.Settings;
import org.groupOne.service.timer.TimeUpdate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Controller  extends TelegramLongPollingBot {
    static final Logger log = Logger.getLogger(Controller.class);
    private final ButtonContainer buttonContainer;
    private static final String BOT_USER_NAME = "GO_IT_CurrencyInfo_bot";
    private static final String TOKEN = "1905777974:AAGOt-2svPaZKinr_VsWGK-sirUgfP4V4No";
    private TimeUpdate timeUpdate;

    public Controller() {
        buttonContainer = new ButtonContainer(new SendMessageBotService(this));
        timeUpdate = new TimeUpdate(this);
        timeUpdate.startTimer();
    }

    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        // log.info(TOKEN);
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String buttonIdentifier;
        Long chatId;
        if (update.hasMessage()) {
            buttonIdentifier = update.getMessage().getText().trim();
            chatId = update.getMessage().getChatId();
            buttonContainer.retrieveButton(buttonIdentifier).execute(update, buttonContainer.getSettingsCurrentUser(chatId));
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            buttonIdentifier = update.getCallbackQuery().getData();
            buttonContainer.retrieveButton(buttonIdentifier).execute(update, buttonContainer.getSettingsCurrentUser(chatId));
        }
    }

    public Map<Long, Settings> getSettings() {
        return buttonContainer.getAlUserSettings();
    }
}
