package org.groupOne;

<<<<<<< HEAD
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Controller());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
=======
import org.groupOne.facade.Facade;
import org.groupOne.nbu_api.NBU_API;
import org.groupOne.privat_api.PrivatApi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Facade facade = new Facade(new PrivatApi(), new NBU_API());
        System.out.println(facade.getInfo("Privat"));
        System.out.println(facade.getInfo("NBU"));
>>>>>>> origin/bank_api
    }
}
