package org.groupOne;

import org.apache.log4j.Logger;
import org.groupOne.Services.ButtonContainer;
import org.groupOne.Services.SendMessageBot;
import org.groupOne.Services.Settings;
import org.groupOne.Services.settings_buttons.BankButton;
import org.groupOne.Services.settings_buttons.CurrencyButton;
import org.groupOne.Services.settings_buttons.InfoButton;
import org.groupOne.Services.settings_buttons.PrecisionButton;
import org.groupOne.Services.settings_buttons.SettingsButton;
import org.groupOne.Services.settings_buttons.StartButton;
import org.groupOne.Services.timer.TimeUpdate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import static org.groupOne.Services.button_enam.ButtonName.*;
import java.util.Map;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Controller  extends TelegramLongPollingBot {

    static final Logger log = Logger.getLogger(Controller.class);
    private static final String BOT_USER_NAME = "GO_IT_CurrencyInfo_bot";
    private static final String TOKEN = "1905777974:AAGOt-2svPaZKinr_VsWGK-sirUgfP4V4No";

    private final ButtonContainer buttonContainer;
    private TimeUpdate timeUpdate;

    public Controller() {
        this.buttonContainer = new ButtonContainer(new SendMessageBot(this));
        timeUpdate = new TimeUpdate(this);
        timeUpdate.startTimer();
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Controller());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        log.info(TOKEN);
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        log.info(BOT_USER_NAME);
        return BOT_USER_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String command;
        Long chatId;
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {

                if (update.getMessage().getText().equals(START.getButtonName())) {
                    command = update.getMessage().getText().trim();
                    chatId = update.getMessage().getChatId();
                    executeCommand(command, chatId, update);
                    log.info("command1 = " + command);
                }
                if (update.getMessage().getText().equals(INFO.getButtonName())) {
                    command = update.getMessage().getText().trim();
                    chatId = update.getMessage().getChatId();
                    executeCommand(command, chatId, update);
                    log.info("command2 = " + command);
                    log.info("get text2 = " + update.getMessage().getText());

                } else if (update.hasCallbackQuery()) {
                    command = update.getMessage().getText().trim();
                    chatId = update.getMessage().getChatId();
                    executeCommand(command, chatId, update);
                    try {
                        execute(menu(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getData()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private SendMessage menu(Long chatId, String data) {

        if (data.equals(SETTINGS.getButtonName())) {
            return new SettingsButton().sendSettingsMenu(chatId);
        }
        if (data.equals("callback_precision")) {
            return new PrecisionButton().sendPrecisionInlineButtons(chatId);
        }
        if (data.equals("callback_bank")) {
            return new BankButton().sendBankInlineButtons(chatId);
        }
        if (data.equals("callback_currency")) {
            return new CurrencyButton().sendCurrencyInlineButtons(chatId);
        }
        if (data.equals(INFO.getButtonName())) {
            return new InfoButton().sendInfoMenu(chatId);
        }
        else {
            return StartButton.sendStartMenu(chatId);
        }
    }

    private void executeCommand(String command,Long chatId, Update update){
        buttonContainer.retrieveButton(command).execute(update,buttonContainer.getSettingsCurrentUser(chatId));
    }

    public Map<Long, Settings> getSettings() {
        return buttonContainer.getAlUserSettings();
    }
}
