package org.groupOne.Services.settings_buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand {
    private final static String MESSAGE_HELP = "Помощь: \n" +
            "❗ ️Получить инфо - получение текущего курса валют в зависимости от настроек.\n\n" +
            "⚙  Настройки - выбор валюты, банка, точности и время оповещения\n\n" +
            "\uD83C\uDFAF   Кол-во знаков после запятой - выбор точности отображения курса (по умолчанию 2)\n\n" +
            "\uD83C\uDFE6   Банк - выбор банка (по умолчанию Приват Банк)\n\n" +
            "\uD83D\uDCB0   Валюта - выбор валюты (по умолчанию USD)\n\n" +
            "\uD83D\uDD57   Время оповещений - выбор времени уведомлений о курсе валют по заданным настройкам (по умолчанию 9:00)\n\n" +
            "\uD83D\uDED1   Выключить уведомления - вкл/выкл уведомлений (по умолчанию выкл)\n";
    public static SendMessage sendHelp(Update update){
        String chatId = update.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(MESSAGE_HELP);
        return sendMessage;
    }
}
