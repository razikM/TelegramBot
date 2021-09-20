package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.service.button.enum_button.ButtonCallBack.*;
import static org.groupOne.service.button.enum_button.ButtonName.*;

public class BackButton implements Button {
    private final SendMessageBotService sendMessageBotService;
    private Integer messageId;

    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    InlineKeyboardButton button1 = new InlineKeyboardButton();
    InlineKeyboardButton button2 = new InlineKeyboardButton();
    InlineKeyboardButton button3 = new InlineKeyboardButton();
    InlineKeyboardButton button4 = new InlineKeyboardButton();

    List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
    List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    public BackButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;
    }

    @Override
    public void execute(Update update, Settings settings) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        messageId = update.getCallbackQuery().getMessage().getMessageId();
        buttonsRow1.clear();
        buttonsRow2.clear();
        rowList.clear();
        sendMessageBotService.EditMessage(chatId, messageId, SETTINGS.getName(), backToSettings());
    }

    private InlineKeyboardMarkup backToSettings() {
        button1.setText(PRECISION.getName());
        button1.setCallbackData(PRECISION_CALLBACK.getCallback());

        button2.setText(BANK.getName());
        button2.setCallbackData(BANK_CALLBACK.getCallback());

        button3.setText(CURRENCY.getName());
        button3.setCallbackData(CURRENCY_CALLBACK.getCallback());

        button4.setText(TIME_UPDATE.getName());
        button4.setCallbackData(TIME_UPDATE_CALLBACK.getCallback());

        buttonsRow1.add(button1);
        buttonsRow1.add(button2);
        buttonsRow2.add(button3);
        buttonsRow2.add(button4);

        rowList.add(buttonsRow1);
        rowList.add(buttonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

}
