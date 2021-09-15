package org.groupOne.Services.settings_buttons;

import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class StartButton {

    private final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private final List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
    private final List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
    private final List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    public final static String START_MESSAGE = "Привет, %s\uD83D\uDC4B. Этот бот поможет отслеживать актуальные курсы валют";

    private ReplyKeyboard createKeyBoard() {

        buttonsRow1.add(InlineKeyboardButton.builder().text(INFO.getButtonName()).callbackData(INFO_DATA.getData()).build());
        buttonsRow2.add(InlineKeyboardButton.builder().text(SETTINGS.getButtonName()).callbackData(SETTINGS_DATA.getData()).build());
        rowList.add(buttonsRow1);
        rowList.add(buttonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
       /* ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(INFO.getButtonName()));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(SETTINGS.getButtonName()));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;*/
    }

    public static SendMessage sendStartMenu(long chatId, Update update) {

        String firstname = update.getMessage().getFrom().getFirstName();
        String START_MESSAGE = "Привет, " + firstname + "\uD83D\uDC4B. Этот бот поможет отслеживать актуальные курсы валют";

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(INFO.getButtonName());
        inlineKeyboardButton1.setCallbackData(INFO_DATA.getData());
        inlineKeyboardButton2.setText(SETTINGS.getButtonName());
        inlineKeyboardButton2.setCallbackData(SETTINGS_DATA.getData());
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(START_MESSAGE);
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
