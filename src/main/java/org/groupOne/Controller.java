package org.groupOne;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

public class Controller extends TelegramLongPollingBot {

    private final List<String> commands = Arrays.asList("/start", "/help", "/stop");

    private Services services;

    public Controller() {
        this.services = new Services();
    }

    @Override
    public String getBotUsername() {
        return "GO_IT_CurrencyInfo_bot";
    }

    @Override
    public String getBotToken() {
        return "1905777974:AAGOt-2svPaZKinr_VsWGK-sirUgfP4V4No";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message.getText().startsWith("/")){
            executeCommand(message.getText(), update);
        } else {
            executeNonCommandOrButton(message.getText());
        }
    }

    private void executeCommand(String command, Update update){
        if(isCommandValid(command)){
//            services.someMethod();
        } else {
//            services.errorMethod();
        }
    }

    private void executeNonCommandOrButton(String request){
//        services.someMethod(request);
    }

    private boolean isCommandValid(String command){
        return commands.contains(command);
    }
}
