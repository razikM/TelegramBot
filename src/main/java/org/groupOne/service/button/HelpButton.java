package org.groupOne.service.button;

import org.groupOne.service.SendMessageBotService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpButton implements Button {
    private final SendMessageBotService sendMessageBotService;
    private final static String MESSAGE_HELP = "Помощь: \n" +
            "✉️  Получить инфо - получение текущего курса валют в зависимости от настроек.\n\n" +
            "⚙️  Настройки - выбор валюты, банка, точности и времея оповещения\n\n" +
            "\uD83C\uDFAF   Кол-во знаков после запятой - выбор точности отображения курса (по умолчанию 2)\n\n" +
            "\uD83C\uDFE6   Банк - выбор банка (по умолчанию НБУ)\n\n" +
            "\uD83D\uDCB0   Валюта - выбор валюты (по умолчанию USD)\n\n" +
            "⏱   Время оповещений - выбор времени уведомлений о курсе валют по заданным настройкам (по умолчанию 9:00)\n\n" +
            "\uD83D\uDD27   Мои настройки - показать текущие настройки\n\n" +
            "✅   Выключить уведомления - вкл/выкл уведомлений (по умолчанию выкл)\n";

    public HelpButton(SendMessageBotService sendMessageBotService) {
        this.sendMessageBotService = sendMessageBotService;
    }

    @Override
    public void execute(Update update, Settings settings) {
        String chatId = update.getMessage().getChatId().toString();
        sendMessageBotService.SendMessage(chatId, MESSAGE_HELP);
    }
}
