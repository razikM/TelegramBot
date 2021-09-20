package org.groupOne.service.timer;

import org.groupOne.Controller;
import org.groupOne.service.button.GetMessageInfo;
import org.groupOne.service.button.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static org.groupOne.service.button.enum_button.ButtonName.*;

public class Task extends TimerTask {
    private Controller controller;
    private GetMessageInfo getMessage = new GetMessageInfo();

    public Task(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        SendMessage sendMessage = new SendMessage();
        StringBuilder message = new StringBuilder();
        Map<Long, Settings> settings = controller.getSettings();
        if (settings.size() == 0)
            return;
        List<Settings> toListSettings = settings.values().stream().collect(Collectors.toList());
        for (Settings st : toListSettings) {
            if (!st.isCheckDisableTimeUpdate()) {
                if (st.getTimeUpdate() == LocalTime.now().getHour()) {
                    Long chatId = st.getChatId();
                    sendMessage.setChatId(chatId.toString());
                    if (st.isCheckNBU()) {
                        message.append(getMessage.getMessageInfo(NBU, st));
                    }
                    if (st.isCheckMonoBank()) {
                        message.append(getMessage.getMessageInfo(MONOBANK, st));
                    }
                    if (st.isCheckPrivatBank()) {
                        message.append(getMessage.getMessageInfo(PRIVATBANK, st));
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


        // message.enableHtml(true);

    }


}
