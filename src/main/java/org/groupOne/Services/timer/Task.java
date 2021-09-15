package org.groupOne.Services.timer;

import org.groupOne.Controller;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class Task extends TimerTask{
private Controller telegramBot;
    public Task(Controller telegramBot) {
      this.telegramBot = telegramBot;
    }

    @Override
    public void run() {
        SendMessage message = new SendMessage();
//        Map<Long, Settings> settings = telegramBot.getSettings();
//        List<Settings> toListSettings = settings.values().stream().toList();
//        for ( Settings st : toListSettings) {
//            if(!st.isCheckDisableTimeUpdate()) {
//                if (st.getTimeUpdate() == LocalTime.now().getHour()) {
//                    Long chatId = st.getChatId();
//                    message.setChatId(chatId.toString());
//                    message.setText("Курс валют USD: 26.7");
//                    try {
//                        telegramBot.execute(message);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }


       // message.enableHtml(true);

    }


}
