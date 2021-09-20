package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.service.button.enum_button.ButtonCallBack.*;
import static org.groupOne.service.button.enum_button.ButtonName.*;

public class CurrencyButton implements Button {
    private final SendMessageBotService sendMessageBotService;
    private static String data;
    private Settings settings;
    private final static String MESSAGE = "\uD83D\uDCB0 Выберете Валюты";
    private final static String CHECK = "✅ ";
    private final static String UNCHECK = "";
    private final static String BACK_EMOJI = "⬅";

    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private InlineKeyboardButton buttonUSD = new InlineKeyboardButton();
    private InlineKeyboardButton buttonEUR = new InlineKeyboardButton();
    private InlineKeyboardButton buttonRUR = new InlineKeyboardButton();
    private InlineKeyboardButton buttonBack = new InlineKeyboardButton();
    private List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    public CurrencyButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;
    }

    @Override
    public void execute(Update update, Settings settings) {
        this.settings = settings;
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        data = update.getCallbackQuery().getData();
        if (data.equals(CURRENCY_CALLBACK.getCallback())) {
            buttonsRow1.clear();
            buttonsRow2.clear();
            rowList.clear();
            sendMessageBotService.EditMessage(chatId, messageId, MESSAGE, createKeyBoard());
        } else {
            sendMessageBotService.EditMessage(chatId, messageId, MESSAGE, editKeyBoard());
        }
    }

    private InlineKeyboardMarkup createKeyBoard() {

        if (settings.isCheckUSD()) {
            buttonUSD.setText(CHECK + USD.getName());
        } else {
            buttonUSD.setText(UNCHECK + USD.getName());
        }
        buttonUSD.setCallbackData(USD_CALLBACK.getCallback());

        if (settings.isCheckEUR()) {
            buttonEUR.setText(CHECK + EUR.getName());
        } else {
            buttonEUR.setText(UNCHECK + EUR.getName());
        }
        buttonEUR.setCallbackData(EUR_CALLBACK.getCallback());

        if (settings.isCheckRUR()) {
            buttonRUR.setText(CHECK + RUR.getName());
        } else {
            buttonRUR.setText(UNCHECK + RUR.getName());
        }
        buttonRUR.setCallbackData(RUR_CALLBACK.getCallback());

        buttonBack.setText(BACK_EMOJI + BACK.getName());
        buttonBack.setCallbackData(BACK_CALLBACK.getCallback());

        buttonsRow1.add(buttonUSD);
        buttonsRow1.add(buttonEUR);
        buttonsRow1.add(buttonRUR);
        buttonsRow2.add(buttonBack);

        rowList.add(buttonsRow1);
        rowList.add(buttonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup editKeyBoard() {
        if (data.equals(USD_CALLBACK.getCallback())) {
            if (settings.isCheckUSD()) {
                buttonUSD.setText(UNCHECK + USD.getName());
                settings.setCheckUSD(false);
            } else {
                buttonUSD.setText(CHECK + USD.getName());
                settings.setCheckUSD(true);
            }
        }
        if (data.equals(EUR_CALLBACK.getCallback())) {
            if (settings.isCheckEUR()) {
                buttonEUR.setText(UNCHECK + EUR.getName());
                settings.setCheckEUR(false);
            } else {
                buttonEUR.setText(CHECK + EUR.getName());
                settings.setCheckEUR(true);
            }
        }
        if (data.equals(RUR_CALLBACK.getCallback())) {
            if (settings.isCheckRUR()) {
                buttonRUR.setText(UNCHECK + RUR.getName());
                settings.setCheckRUR(false);
            } else {
                buttonRUR.setText(CHECK + RUR.getName());
                settings.setCheckRUR(true);
            }
        }
        return inlineKeyboardMarkup;
    }

}
