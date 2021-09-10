package org.groupOne.Services;

import org.groupOne.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendMessageBot {
    private final Controller controller;

    public SendMessageBot(Controller controller) {
        this.controller = controller;
    }

public void SendMessage(String chatId, String message, ReplyKeyboard replyKeyboard){
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
public void EditMessage(){

}
}
