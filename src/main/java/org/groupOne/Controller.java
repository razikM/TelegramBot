package org.groupOne;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.groupOne.Services.Settings;
import org.groupOne.Services.settings_buttons.BankButton;
import org.groupOne.Services.settings_buttons.CurrencyButton;
import org.groupOne.Services.settings_buttons.InfoButton;
import org.groupOne.Services.settings_buttons.PrecisionButton;
import org.groupOne.Services.settings_buttons.SettingsButton;
import org.groupOne.Services.settings_buttons.StartButton;
import org.groupOne.Services.settings_buttons.TimeUpdateButton;
import org.groupOne.Services.settings_buttons.check_buttons.PrecisionCheck;
import org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_banks.BankCheck;
import org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_currency.CurrencyCheck;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Controller  extends TelegramLongPollingBot {

    static final Logger log = Logger.getLogger(Controller.class);
    private static final List<Settings> settingsList = new ArrayList<Settings>();
    private static final String BOT_USER_NAME = "GO_IT_CurrencyInfo_bot";
    private static final String TOKEN = "1905777974:AAGOt-2svPaZKinr_VsWGK-sirUgfP4V4No";
    private BankButton bankButton = new BankButton();

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


        if (update.hasMessage()) {

            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals(START.getButtonName())) {
//          sendMsg(update.getMessage());
                    try {

                        execute(StartButton.sendStartMenu(update.getMessage().getChatId(), update));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            log.info("update1 = " + update.getCallbackQuery().getData());
            log.info("messageID1 = " + update.getCallbackQuery().getMessage().getMessageId());
            String data = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

            try {
                if (data.equals(SETTINGS_DATA.getData()) || data.equals(PRECISION_DATA.getData()) || data.equals(BANK_DATA.getData()) ||
                    data.equals(CURRENCY_DATA.getData()) || data.equals(INFO_DATA.getData()) || data.equals(TIME_UPDATE_DATA.getData())) {
                    execute(menu(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getData(), update));
                } else {
                    log.info("update2 = " + update.getCallbackQuery().getData());
                    log.info("messageID2 = " + update.getCallbackQuery().getMessage().getMessageId());
                    EditMessageText editMessageText = subMenu(chatId, data, messageId);
                    log.info("editMessageText = " + editMessageText);
                    if(editMessageText != null) {
                        execute(editMessageText);
                    }
                }

            } catch (TelegramApiException e) {
                log.error(e);
                e.printStackTrace();
            }
        }
    }

    private SendMessage menu(Long chatId, String data, Update update) {

        if (data.equals(SETTINGS_DATA.getData())) {
            return new SettingsButton().sendSettingsMenu(chatId);
        }
        if (data.equals(PRECISION_DATA.getData())) {
            return new PrecisionButton().sendPrecisionInlineButtons(chatId);
        }
        if (data.equals(BANK_DATA.getData())) {
            return new BankButton().sendBankInlineButtons(chatId);
        }
        if (data.equals(CURRENCY_DATA.getData())) {
            return new CurrencyButton().sendCurrencyInlineButtons(chatId);
        }
        if (data.equals(INFO_DATA.getData())) {
            return new InfoButton().sendInfoMenu(chatId);
        }
        if (data.equals(TIME_UPDATE_DATA.getData())) {
            return new TimeUpdateButton().sendTimeUpdateMenu(chatId);
        }
        else return StartButton.sendStartMenu(chatId, update);
    }

    private EditMessageText subMenu(Long chatId, String data, Integer messageId) {

        List<Settings> listSettings = settingsList.stream()
            .filter(t -> t.getChatId()
                .equals(chatId)).collect(Collectors.toList());
        Settings settings;

        if(listSettings.isEmpty()){
            settings = new Settings(chatId);
            settingsList.add(settings);
        } else {
            settings = listSettings.get(0);
        }
        log.info("precision: " + settings.getPrecision());

        if (data.equals(PRECISION_TWO_DATA.getData()) && settings.getPrecision() != 2  ||
            data.equals(PRECISION_THREE_DATA.getData()) && settings.getPrecision() != 3 ||
            data.equals(PRECISION_FOUR_DATA.getData()) && settings.getPrecision() != 4)
        {
            switch (data) {
                case "callback_precision_two":
                    settings.setPrecision(2);
                    break;
                case "callback_precision_three":
                    settings.setPrecision(3);
                    break;
                case "callback_precision_four":
                    settings.setPrecision(4);
                    break;
                default:
                    break;
            }
            return new PrecisionCheck().sendPrecisionInlineButtonsChecked(chatId, data, messageId);
        } else if (settings.getPrecision() == 0) {
            settings.setPrecision(2);
            return new PrecisionCheck().sendPrecisionInlineButtonsChecked(chatId, PRECISION_TWO_DATA.getData(), messageId);
        }

        if (data.equals(USD_DATA.getData()) || data.equals(EUR_DATA.getData()) || data.equals(RUB_DATA.getData())) {
            return new CurrencyCheck().sendCurrencyInlineButtonsChecked(chatId, data, messageId);
        }
        else if (data.equals(NBU_DATA.getData()) && !settings.isCheckNBU() ||
                 data.equals(PRIVAT_BANK_DATA.getData()) && settings.isCheckPrivatBank() ||
                 data.equals(MONO_BANK_DATA.getData()) && !settings.isCheckMonoBank()) {
            {
                switch (data) {
                    case "callback_nbu_bank":
                        settings.isCheckNBU();
                        break;
                    case "callback_privat_bank":
                        settings.isCheckPrivatBank();
                        break;
                    case "callback_mono_bank":
                        settings.isCheckMonoBank();
                        break;
                    default:
                        break;
                }
                return new BankCheck().sendBankInlineButtonsChecked(chatId, data, messageId);
            }
        }
        return null;
    }

    private void sendMsg(Message msg) {

        SendMessage s = new SendMessage();
        s.setChatId(String.valueOf(msg.getChatId()));
        s.setText("Старт!");

        try {
            execute(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

//    private void executeCommand(String command,Long chatId, Update update){
//        buttonContainer.retrieveButton(command).execute(update,buttonContainer.getSettingsCurrentUser(chatId));
//    }
//
//    public Map<Long, Settings> getSettings() {
//        return buttonContainer.getAlUserSettings();
//    }
}
