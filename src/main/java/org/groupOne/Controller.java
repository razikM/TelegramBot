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
import org.groupOne.Services.settings_buttons.check_buttons.DisableTimeUpdateCheck;
import org.groupOne.Services.settings_buttons.check_buttons.PrecisionCheck;
import org.groupOne.Services.settings_buttons.check_buttons.TimeUpdateCheck;
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
    Settings settings;

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
            String requestCommand = update.getMessage().getText();
            long chat_ID = update.getMessage().getChatId();

            List<Settings> listSettings = settingsList.stream()
                .filter(t -> t.getChatId()
                    .equals(chat_ID)).collect(Collectors.toList());
//            Settings settings;

            if(listSettings.isEmpty()){
                settings = new Settings(chat_ID);
                settingsList.add(settings);
            } else {
                settings = listSettings.get(0);
            }

            if (update.getMessage().hasText()) {
                if (requestCommand.equals(START.getButtonName())) {
//          sendMsg(update.getMessage());
                    try {

                        execute(StartButton.sendStartMenu(update.getMessage().getChatId(), update));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else if (requestCommand.equals(TIME_UPDATE_NINE.getButtonName()) && settings.getTimeUpdate() != 9 ||
                         requestCommand.equals(TIME_UPDATE_TEN.getButtonName()) && settings.getTimeUpdate() != 10 ||
                         requestCommand.equals(TIME_UPDATE_ELEVEN.getButtonName()) && settings.getTimeUpdate() != 11 ||
                         requestCommand.equals(TIME_UPDATE_TWELVE.getButtonName()) && settings.getTimeUpdate() != 12 ||
                         requestCommand.equals(TIME_UPDATE_THIRTEEN.getButtonName()) && settings.getTimeUpdate() != 13 ||
                         requestCommand.equals(TIME_UPDATE_FOURTEEN.getButtonName()) && settings.getTimeUpdate() != 14 ||
                         requestCommand.equals(TIME_UPDATE_FIFTEEN.getButtonName()) && settings.getTimeUpdate() != 15 ||
                         requestCommand.equals(TIME_UPDATE_SIXTEEN.getButtonName()) && settings.getTimeUpdate() != 16 ||
                         requestCommand.equals(TIME_UPDATE_SEVENTEEN.getButtonName()) && settings.getTimeUpdate() != 17 ||
                         requestCommand.equals(TIME_UPDATE_EIGHTEEN.getButtonName()) && settings.getTimeUpdate() != 18
                ) {
                    switch (requestCommand) {
                        case "9":
                            settings.setTimeUpdate(9);
                            break;
                        case "10":
                            settings.setTimeUpdate(10);
                            break;
                        case "11":
                            settings.setTimeUpdate(11);
                            break;
                        case "12":
                            settings.setTimeUpdate(12);
                            break;
                        case "13":
                            settings.setTimeUpdate(13);
                            break;
                        case "14":
                            settings.setTimeUpdate(14);
                            break;
                        case "15":
                            settings.setTimeUpdate(15);
                            break;
                        case "16":
                            settings.setTimeUpdate(16);
                            break;
                        case "17":
                            settings.setTimeUpdate(17);
                            break;
                        case "18":
                            settings.setTimeUpdate(18);
                            break;
                        default:
                            break;
                    }
                    log.info("new_mess data = " + requestCommand);
                    log.info("settings_getTimeUpdate = " + settings.getTimeUpdate());
                    try {
                        execute(TimeUpdateCheck.sendTimeUpdateMenu(chat_ID, requestCommand));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else if ((requestCommand.equals(TIME_UPDATE_DISABLE.getButtonName()) && settings.isCheckDisableTimeUpdate()) ||
                        (requestCommand.equals(TIME_UPDATE_ENABLE.getButtonName()) && settings.isCheckDisableTimeUpdate())
                ) {
                    try {
                        execute(DisableTimeUpdateCheck.sendTimeUpdateDisable(chat_ID, requestCommand));
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
//        Settings settings;

        if (listSettings.isEmpty()) {
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
        log.info("settings_NBU = " + settings.isCheckNBU());
        log.info("settings_Privat = " + settings.isCheckPrivatBank());
        log.info("settings_MONO = " + settings.isCheckMonoBank());
        log.info("--================================================================--");
        log.info("settings_USD = " + settings.isCheckUSD());
        log.info("settings_EUR = " + settings.isCheckEUR());
        log.info("settings_RUB = " + settings.isCheckRUB());
        if (data.equals(USD_DATA.getData()) || data.equals(EUR_DATA.getData()) || data.equals(RUB_DATA.getData()))
        {
            switch (data) {
                case "callback_USD":
                    settings.setCheckUSD(!settings.isCheckUSD());
                    break;
                case "callback_EUR":
                    settings.setCheckEUR(!settings.isCheckEUR());
                    break;
                case "callback_RUB":
                    settings.setCheckRUB(!settings.isCheckRUB());
                    break;
                default:
                    break;
            }
            return new CurrencyCheck().sendCurrencyInlineButtonsChecked(chatId, data, messageId);
        }
        else if (data.equals(NBU_DATA.getData()) && !settings.isCheckNBU() ||
                 data.equals(PRIVAT_BANK_DATA.getData()) && settings.isCheckPrivatBank() ||
                 data.equals(MONO_BANK_DATA.getData()) && !settings.isCheckMonoBank()) {
            {
                switch (data) {
                    case "callback_nbu_bank":
                        settings.setCheckNBU(true);
                        break;
                    case "callback_privat_bank":
                        settings.setCheckPrivatBank(true);
                        break;
                    case "callback_mono_bank":
                        settings.setCheckMonoBank(true);
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
}
