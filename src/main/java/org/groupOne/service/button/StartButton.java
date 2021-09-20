package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.service.button.enum_button.ButtonCallBack.*;
import static org.groupOne.service.button.enum_button.ButtonName.*;

public class StartButton implements Button {
    private final SendMessageBotService sendMessageBotService;
    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private InlineKeyboardButton buttonGetInfo = new InlineKeyboardButton();
    private InlineKeyboardButton buttonSettings = new InlineKeyboardButton();
    private List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    public final static String START_MESSAGE = "Привет, %s. Этот бот поможет отслеживать актуальные курсы валют. ";

    public StartButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;
    }

    @Override
    public void execute(Update update, Settings settings) {
        String chatId = update.getMessage().getChatId().toString();
        String firstname = update.getMessage().getFrom().getFirstName();
        sendMessageBotService.SendMessage(chatId, String.format(START_MESSAGE, firstname), createKeyBoard());
    }

    private InlineKeyboardMarkup createKeyBoard() {
        buttonGetInfo.setText(INFO.getName());
        buttonSettings.setText(SETTINGS.getName());
        buttonGetInfo.setCallbackData(INFO_CALLBACK.getCallback());
        buttonSettings.setCallbackData(SETTINGS_CALLBACK.getCallback());
        buttonsRow1.add(buttonGetInfo);
        buttonsRow2.add(buttonSettings);
        rowList.add(buttonsRow1);
        rowList.add(buttonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
