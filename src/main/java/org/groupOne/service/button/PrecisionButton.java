package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.service.button.enum_button.ButtonCallBack.*;
import static org.groupOne.service.button.enum_button.ButtonName.*;

public class PrecisionButton implements Button {
    private final SendMessageBotService sendMessageBotService;
    private static String data;
    private Settings settings;
    private Integer messageId;
    private final static String MESSAGE = "\uD83C\uDFAF Выберете кол-во знаков после запятой";
    private final static String CHECK = "✅ ";
    private final static String UNCHECK = "";
    private final static String BACK_EMOJI = "⬅";

    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private InlineKeyboardButton buttonTWO = new InlineKeyboardButton();
    private InlineKeyboardButton buttonTHREE = new InlineKeyboardButton();
    private InlineKeyboardButton buttonFOUR = new InlineKeyboardButton();
    private InlineKeyboardButton buttonBack = new InlineKeyboardButton();

    private List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();

    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    public PrecisionButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;
    }

    @Override
    public void execute(Update update, Settings settings) {
        this.settings = settings;
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        messageId = update.getCallbackQuery().getMessage().getMessageId();
        data = update.getCallbackQuery().getData();
        if (data.equals(PRECISION_CALLBACK.getCallback())) {
            buttonsRow1.clear();
            buttonsRow2.clear();
            rowList.clear();
            sendMessageBotService.EditMessage(chatId, messageId, MESSAGE, createKeyBoard());
        } else if ((data.equals(PRECISION_TWO_CALLBACK.getCallback()) && settings.getPrecision() != 2) ||
                (data.equals(PRECISION_THREE_CALLBACK.getCallback()) && settings.getPrecision() != 3) ||
                (data.equals(PRECISION_FOUR_CALLBACK.getCallback()) && settings.getPrecision() != 4)) {
            sendMessageBotService.EditMessage(chatId, messageId, MESSAGE, editKeyBoard());
        }
    }

    private InlineKeyboardMarkup createKeyBoard() {

        switch (settings.getPrecision()) {
            case 2:
                buttonTWO.setText(CHECK + PRECISION_TWO.getName());
                buttonTHREE.setText(UNCHECK + PRECISION_THREE.getName());
                buttonFOUR.setText(UNCHECK + PRECISION_FOUR.getName());
                break;
            case 3:
                buttonTWO.setText(UNCHECK + PRECISION_TWO.getName());
                buttonTHREE.setText(CHECK + PRECISION_THREE.getName());
                buttonFOUR.setText(UNCHECK + PRECISION_FOUR.getName());
                break;
            case 4:
                buttonTWO.setText(UNCHECK + PRECISION_TWO.getName());
                buttonTHREE.setText(UNCHECK + PRECISION_THREE.getName());
                buttonFOUR.setText(CHECK + PRECISION_FOUR.getName());
                break;
        }
        buttonBack.setText(BACK_EMOJI + BACK.getName());
        buttonBack.setCallbackData(BACK_CALLBACK.getCallback());

        buttonTWO.setCallbackData(PRECISION_TWO_CALLBACK.getCallback());
        buttonTHREE.setCallbackData(PRECISION_THREE_CALLBACK.getCallback());
        buttonFOUR.setCallbackData(PRECISION_FOUR_CALLBACK.getCallback());

        buttonsRow1.add(buttonTWO);
        buttonsRow1.add(buttonTHREE);
        buttonsRow1.add(buttonFOUR);
        buttonsRow2.add(buttonBack);

        rowList.add(buttonsRow1);
        rowList.add(buttonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup editKeyBoard() {
        if (data.equals(PRECISION_TWO_CALLBACK.getCallback())) {
            buttonTWO.setText(CHECK + PRECISION_TWO.getName());
            buttonTHREE.setText(UNCHECK + PRECISION_THREE.getName());
            buttonFOUR.setText(UNCHECK + PRECISION_FOUR.getName());
            settings.setPrecision(2);
        }
        if (data.equals(PRECISION_THREE_CALLBACK.getCallback())) {
            buttonTWO.setText(UNCHECK + PRECISION_TWO_CALLBACK.getCallback());
            buttonTHREE.setText(CHECK + PRECISION_THREE_CALLBACK.getCallback());
            buttonFOUR.setText(UNCHECK + PRECISION_FOUR_CALLBACK.getCallback());
            settings.setPrecision(3);
        }
        if (data.equals(PRECISION_FOUR_CALLBACK.getCallback())) {
            buttonTWO.setText(UNCHECK + PRECISION_TWO.getName());
            buttonTHREE.setText(UNCHECK + PRECISION_THREE.getName());
            buttonFOUR.setText(CHECK + PRECISION_FOUR.getName());
            settings.setPrecision(4);
        }
        return inlineKeyboardMarkup;
    }
}
