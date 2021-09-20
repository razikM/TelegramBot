package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.service.button.enum_button.ButtonCallBack.*;
import static org.groupOne.service.button.enum_button.ButtonName.*;

public class BankButton implements Button {
    private final SendMessageBotService sendMessageBotService;
    private static String data;
    private Settings settings;
    private final static String MESSAGE = "\uD83C\uDFE6 Выберете Банки";
    private final static String CHECK = "✅";
    private final static String UNCHECK = "";
    private final static String BACK_EMOJI = "⬅";

    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private InlineKeyboardButton buttonNBU = new InlineKeyboardButton();
    private InlineKeyboardButton buttonMONO = new InlineKeyboardButton();
    private InlineKeyboardButton buttonPRIVAT = new InlineKeyboardButton();
    private InlineKeyboardButton buttonBack = new InlineKeyboardButton();
    private List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();

    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    public BankButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;

    }

    @Override
    public void execute(Update update, Settings settings) {
        this.settings = settings;
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        data = update.getCallbackQuery().getData();
        if (data.equals(BANK_CALLBACK.getCallback())) {
            buttonsRow1.clear();
            buttonsRow2.clear();
            rowList.clear();
            sendMessageBotService.EditMessage(chatId, messageId, MESSAGE, createKeyBoard());
        } else if ((data.equals(NBU_CALLBACK.getCallback()) && !settings.isCheckNBU()) ||
                (data.equals(MONOBANK_CALLBACK.getCallback()) && !settings.isCheckMonoBank()) ||
                (data.equals(PRIVATBANK_CALLBACK.getCallback()) && !settings.isCheckPrivatBank())) {
            sendMessageBotService.EditMessage(chatId, messageId, MESSAGE, editKeyBoard());
        }
    }

    private InlineKeyboardMarkup createKeyBoard() {

        if (settings.isCheckNBU()) {
            buttonNBU.setText(CHECK + NBU.getName());
        } else {
            buttonNBU.setText(UNCHECK + NBU.getName());
        }
        buttonNBU.setCallbackData(NBU_CALLBACK.getCallback());

        if (settings.isCheckMonoBank()) {
            buttonMONO.setText(CHECK + MONOBANK.getName());
        } else {
            buttonMONO.setText(UNCHECK + MONOBANK.getName());
        }
        buttonMONO.setCallbackData(MONOBANK_CALLBACK.getCallback());

        if (settings.isCheckPrivatBank()) {
            buttonPRIVAT.setText(CHECK + PRIVATBANK.getName());
        } else {
            buttonPRIVAT.setText(UNCHECK + PRIVATBANK.getName());
        }
        buttonPRIVAT.setCallbackData(PRIVATBANK_CALLBACK.getCallback());
        buttonBack.setText(BACK_EMOJI + BACK.getName());
        buttonBack.setCallbackData(BACK_CALLBACK.getCallback());

        buttonsRow1.add(buttonNBU);
        buttonsRow1.add(buttonMONO);
        buttonsRow1.add(buttonPRIVAT);
        buttonsRow2.add(buttonBack);

        rowList.add(buttonsRow1);
        rowList.add(buttonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup editKeyBoard() {

        if (data.equals(NBU_CALLBACK.getCallback())) {
            if (!settings.isCheckNBU()) {
                buttonNBU.setText(CHECK + NBU.getName());
                buttonMONO.setText(UNCHECK + MONOBANK.getName());
                buttonPRIVAT.setText(UNCHECK + PRIVATBANK.getName());
                settings.setCheckNBU(true);
                settings.setCheckMonoBank(false);
                settings.setCheckPrivatBank(false);
            }
        }
        if (data.equals(MONOBANK_CALLBACK.getCallback())) {
            if (!settings.isCheckMonoBank()) {
                buttonNBU.setText(UNCHECK + NBU.getName());
                buttonMONO.setText(CHECK + MONOBANK.getName());
                buttonPRIVAT.setText(UNCHECK + PRIVATBANK.getName());
                settings.setCheckNBU(false);
                settings.setCheckMonoBank(true);
                settings.setCheckPrivatBank(false);
            }
        }
        if (data.equals(PRIVATBANK_CALLBACK.getCallback())) {
            if (!settings.isCheckPrivatBank()) {
                buttonNBU.setText(UNCHECK + NBU.getName());
                buttonMONO.setText(UNCHECK + MONOBANK.getName());
                buttonPRIVAT.setText(CHECK + PRIVATBANK.getName());
                settings.setCheckNBU(false);
                settings.setCheckMonoBank(false);
                settings.setCheckPrivatBank(true);
            }
        }
        return inlineKeyboardMarkup;
    }
}
