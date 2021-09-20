package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.service.button.enum_button.ButtonCallBack.*;
import static org.groupOne.service.button.enum_button.ButtonName.*;


public class GetInfoButton implements Button {
    private final SendMessageBotService sendMessageBotService;

    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private InlineKeyboardButton buttonGetInfo = new InlineKeyboardButton();
    private InlineKeyboardButton buttonSettings = new InlineKeyboardButton();
    private List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    private StringBuilder infoMessage = new StringBuilder();
    private Settings settings;
    private GetMessageInfo getdMessage = new GetMessageInfo();

    public GetInfoButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;
    }

    @Override
    public void execute(Update update, Settings settings) {
        String chatId;
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId().toString();
        } else {
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        }
        this.settings = settings;
        if (!settings.isCheckNBU() && !settings.isCheckMonoBank() && !settings.isCheckPrivatBank()) {
            settings.setCheckNBU(true);
        }
        if (settings.isCheckNBU()) {
            infoMessage.append(getdMessage.getMessageInfo(NBU, settings));
        }
        if (settings.isCheckMonoBank()) {
            infoMessage.append(getdMessage.getMessageInfo(MONOBANK, settings));
        }

        if (settings.isCheckPrivatBank()) {
            infoMessage.append(getdMessage.getMessageInfo(PRIVATBANK, settings));
        }
        buttonsRow1.clear();
        buttonsRow2.clear();
        rowList.clear();
        sendMessageBotService.SendMessage(chatId, infoMessage.toString(), createKeyBoard());
        infoMessage.delete(0, infoMessage.length());
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
