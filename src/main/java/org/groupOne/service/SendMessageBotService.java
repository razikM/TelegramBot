package org.groupOne.service;

import org.groupOne.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendMessageBotService {
    private final Controller controller;


    public SendMessageBotService(Controller controller) {
        this.controller = controller;
    }

    public void SendMessage(String chatId, String message, ReplyKeyboard replyKeyboard) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboard);

        try {
          controller.execute(sendMessage);

        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void SendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            controller.execute(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    public void EditMessage(String chatId, Integer messageId, String message, InlineKeyboardMarkup replyKeyboard) {
        EditMessageText edit = new EditMessageText();
        edit.setChatId(chatId);
        edit.setMessageId(messageId);
        edit.setReplyMarkup(replyKeyboard);
        edit.setText(message);
        try {
            controller.execute(edit);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
