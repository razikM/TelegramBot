package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.service.button.enum_button.ButtonCallBack.*;
import static org.groupOne.service.button.enum_button.ButtonName.*;

public class TimeUpdateButton implements Button {
    private final SendMessageBotService sendMessageBotService;
    private static String data;
    private Settings settings;
    private final static String MESSAGE = "⏱  Выберете время оповещений";
    private final static String CHECK = "✅ ";
    private final static String BACK_EMOJI = "⬅";
    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
    private List<InlineKeyboardButton> buttonsRow5 = new ArrayList<>();

    private List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    public TimeUpdateButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;
        buttonsRow1.add(InlineKeyboardButton.builder().text(TIME_UPDATE_NINE.getName()).callbackData(TIME_UPDATE_NINE_CALLBACK.getCallback()).build());
        buttonsRow1.add(InlineKeyboardButton.builder().text(TIME_UPDATE_THEN.getName()).callbackData(TIME_UPDATE_THEN_CALLBACK.getCallback()).build());
        buttonsRow1.add(InlineKeyboardButton.builder().text(TIME_UPDATE_ELEVEN.getName()).callbackData(TIME_UPDATE_ELEVEN_CALLBACK.getCallback()).build());

        buttonsRow2.add(InlineKeyboardButton.builder().text(TIME_UPDATE_TWELVE.getName()).callbackData(TIME_UPDATE_TWELVE_CALLBACK.getCallback()).build());
        buttonsRow2.add(InlineKeyboardButton.builder().text(TIME_UPDATE_THIRTEEN.getName()).callbackData(TIME_UPDATE_THIRTEEN_CALLBACK.getCallback()).build());
        buttonsRow2.add(InlineKeyboardButton.builder().text(TIME_UPDATE_FOURTEEN.getName()).callbackData(TIME_UPDATE_FOURTEEN_CALLBACK.getCallback()).build());

        buttonsRow3.add(InlineKeyboardButton.builder().text(TIME_UPDATE_FIFTEEN.getName()).callbackData(TIME_UPDATE_FIFTEEN_CALLBACK.getCallback()).build());
        buttonsRow3.add(InlineKeyboardButton.builder().text(TIME_UPDATE_SIXTEEN.getName()).callbackData(TIME_UPDATE_SIXTEEN_CALLBACK.getCallback()).build());
        buttonsRow3.add(InlineKeyboardButton.builder().text(TIME_UPDATE_SEVENTEEN.getName()).callbackData(TIME_UPDATE_SEVENTEEN_CALLBACK.getCallback()).build());

        buttonsRow4.add(InlineKeyboardButton.builder().text(TIME_UPDATE_EIGHTEEN.getName()).callbackData(TIME_UPDATE_EIGHTEEN_CALLBACK.getCallback()).build());
        buttonsRow4.add(InlineKeyboardButton.builder().text(TIME_UPDATE_DISABLE.getName()).callbackData(TIME_UPDATE_DISABLE_CALLBACK.getCallback()).build());
        buttonsRow5.add(InlineKeyboardButton.builder().text(BACK.getName()).callbackData(BACK_CALLBACK.getCallback()).build());

        rowList.add(buttonsRow1);
        rowList.add(buttonsRow2);
        rowList.add(buttonsRow3);
        rowList.add(buttonsRow4);
        rowList.add(buttonsRow5);
    }

    @Override
    public void execute(Update update, Settings settings) {
        this.settings = settings;
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        data = update.getCallbackQuery().getData();
        if (data.equals(TIME_UPDATE_CALLBACK.getCallback())) {
            sendMessageBotService.EditMessage(chatId, messageId,MESSAGE, createKeyBoard());
        } else {
            sendMessageBotService.EditMessage(chatId, messageId, MESSAGE, editKeyBoard());
        }
    }

    private InlineKeyboardMarkup createKeyBoard() {
        for (List<InlineKeyboardButton> row : rowList) {
            for (InlineKeyboardButton bt : row) {
                if (bt.getCallbackData().equals(settings.getTimeUpdate().toString())) {
                    bt.setText(CHECK + bt.getCallbackData());
                } else {
                    bt.setText(bt.getCallbackData());
                }
                if (bt.getCallbackData().equals(TIME_UPDATE_DISABLE_CALLBACK.getCallback())) {
                    if (settings.isCheckDisableTimeUpdate()) {
                        bt.setText(CHECK + TIME_UPDATE_DISABLE.getName());
                    } else {
                        bt.setText(TIME_UPDATE_DISABLE.getName());
                    }
                }
                if (bt.getCallbackData().equals(BACK_CALLBACK.getCallback())) {
                    bt.setText(BACK_EMOJI + BACK.getName());
                }
            }
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup editKeyBoard() {
        if (data.equals(TIME_UPDATE_DISABLE_CALLBACK.getCallback())) {
            for (InlineKeyboardButton bt : buttonsRow4) {
                if (bt.getCallbackData().equals(TIME_UPDATE_DISABLE_CALLBACK.getCallback())) {
                    if (settings.isCheckDisableTimeUpdate()) {
                        bt.setText(TIME_UPDATE_DISABLE.getName());
                        settings.setCheckDisableTimeUpdate(false);
                    } else {
                        bt.setText(CHECK + TIME_UPDATE_DISABLE.getName());
                        settings.setCheckDisableTimeUpdate(true);
                    }
                }
            }
        } else {
            String previewButtonSet = settings.getTimeUpdate().toString();
            for (List<InlineKeyboardButton> row : rowList) {
                for (InlineKeyboardButton bt : row) {
                    if (bt.getCallbackData().equals(previewButtonSet)) {
                        bt.setText(bt.getCallbackData());
                    } else {
                        if (bt.getCallbackData().equals(data)) {
                            bt.setText(CHECK + bt.getCallbackData());
                            Integer time = Integer.parseInt(bt.getCallbackData());
                            settings.setTimeUpdate(time);
                        }
                    }
                }
            }
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
