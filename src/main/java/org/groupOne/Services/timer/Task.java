package org.groupOne.Services.timer;

import org.groupOne.ApplicationSettings;
import org.groupOne.Controller;
import org.groupOne.Services.GetMessageInfo;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.time.LocalTime;
import java.util.List;
import java.util.TimerTask;

import static org.groupOne.Services.button_enam.ButtonName.*;

public class Task extends TimerTask{
    private Controller controller;
    private GetMessageInfo getMessage = new GetMessageInfo();
    public Task(Controller telegramBot) {
        this.controller = telegramBot;
    }

    @Override
    public void run() {
        SendMessage sendMessage = new SendMessage();
        StringBuilder message = new StringBuilder();
        List<Settings> toListSettings = ApplicationSettings.settingsList;
        for (Settings st : toListSettings) {
            if (!st.isCheckDisableTimeUpdate()) {
               if (st.getTimeUpdate() == LocalTime.now().getHour()) {
                    Long chatId = st.getChatId();
                    sendMessage.setChatId(chatId.toString());
                    if (st.isCheckNBU()) {
                        message.append(getMessage.getMessageInfo(NBU, st));
                    }
                    if (st.isCheckMonoBank()) {
                        message.append(getMessage.getMessageInfo(MONO_BANK, st));
                    }
                    if (st.isCheckPrivatBank()) {
                        message.append(getMessage.getMessageInfo(PRIVAT_BANK, st));
                    }
                    sendMessage.setText(message.toString());
                    try {
                        controller.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    message.delete(0, message.length());
                }
            }

        }
    }
}