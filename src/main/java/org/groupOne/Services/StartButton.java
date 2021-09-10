package org.groupOne.Services;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static org.groupOne.Services.ButtonName.*;

public class StartButton implements Button{
    private final SendMessageBot sendMessageBot;
    public final static String START_MESSAGE = "Привет, ";
    public StartButton(SendMessageBot sendMessageBotService) {
        this.sendMessageBot = sendMessageBotService;
    }

    @Override
    public void execute(Update update, Settings settings) {
        String chatId = update.getMessage().getChatId().toString();
        String firstname = update.getMessage().getFrom().getFirstName();
        sendMessageBot.SendMessage(chatId,START_MESSAGE + firstname,createKeyBoard());
    }

    private ReplyKeyboard createKeyBoard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
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
        return replyKeyboardMarkup;
    }

}
