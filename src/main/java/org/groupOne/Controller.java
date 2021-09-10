package org.groupOne;

import org.groupOne.Services.ButtonContainer;
import org.groupOne.Services.SendMessageBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Controller  extends TelegramLongPollingBot {
    private final ButtonContainer buttonContainer;

    public Controller() {
        this.buttonContainer = new ButtonContainer(new SendMessageBot(this));
    }

    @Override
    public String getBotToken() {
        return "1905777974:AAGOt-2svPaZKinr_VsWGK-sirUgfP4V4No";
    }
    @Override
    public String getBotUsername() {
        return "GO_IT_CurrencyInfo_bot";
    }
    @Override
    public void onUpdateReceived(Update update) {
        String command;
        Long chatId;
        if (update.hasMessage()) {
            command = update.getMessage().getText().trim();
            chatId = update.getMessage().getChatId();
            executeCommand(command,chatId,update);
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            command = update.getCallbackQuery().getData().trim();
            executeCommand(command,chatId,update);
        }
    }
    private void executeCommand(String command,Long chatId, Update update){
        buttonContainer.retrieveButton(command).execute(update,buttonContainer.getSettingsCurrentUser(chatId));
    }
}
