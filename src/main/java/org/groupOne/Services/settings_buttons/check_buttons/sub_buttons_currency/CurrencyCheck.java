package org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_currency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_banks.BankCheck;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class CurrencyCheck {

  InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
  InlineKeyboardButton buttonCurrencyUSD = new InlineKeyboardButton();
  InlineKeyboardButton buttonCurrencyEUR = new InlineKeyboardButton();
  InlineKeyboardButton buttonCurrencyRUB = new InlineKeyboardButton();
  List<InlineKeyboardButton> rowInlineCurrencyUSD  = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineCurrencyEUR = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineCurrencyRUB = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowsInlineCurrency = new ArrayList<>();
  EditMessageText new_message = new EditMessageText();
//  static List<Settings> settingsList = new ArrayList<Settings>();

  static final Logger log = Logger.getLogger(CurrencyCheck.class);

  public EditMessageText sendCurrencyInlineButtonsChecked(long chatId, String data, Integer messageId) {

    String SEND_TEXT_CHECK_BANK = "\u200B\uD83D\uDCB4\u200B\uD83D\uDCB1\u200B\uD83D\uDCB5\u200B Выберите валюту: ";
    List<Settings> listSettings = ApplicationSettings.settingsList.stream()
        .filter(t -> t.getChatId().equals(chatId))
        .collect(Collectors.toList());
    Settings settings;

    if(listSettings.isEmpty()){
      settings = new Settings(chatId);
      ApplicationSettings.settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }

    if(data.equals(USD_DATA.getData())){
      settings.setCheckUSD(!settings.isCheckUSD());
    } else if(data.equals(EUR_DATA.getData())){
      settings.setCheckEUR(!settings.isCheckEUR());
    } else if(data.equals(RUB_DATA.getData())){
      settings.setCheckRUB(!settings.isCheckRUB());
    }

    settings.setCheckUSD(settings.isCheckUSD());
    settings.setCheckEUR(settings.isCheckEUR());
    settings.setCheckRUB(settings.isCheckRUB());

    buttonCurrencyUSD = new UsdButtonCheck().sendUsdBankButtonCheck(settings.isCheckUSD());
    settings.setCheckUSD(settings.isCheckUSD());
    buttonCurrencyEUR = new EurButtonCheck().sendEurBankButtonCheck(settings.isCheckEUR());
    settings.setCheckEUR(settings.isCheckEUR());
    buttonCurrencyRUB = new RubButtonCheck().sendRubButtonCheck(settings.isCheckRUB());
    settings.setCheckRUB(settings.isCheckRUB());

    rowInlineCurrencyUSD.add(buttonCurrencyUSD);
    rowInlineCurrencyEUR.add(buttonCurrencyEUR);
    rowInlineCurrencyRUB.add(buttonCurrencyRUB);

    rowsInlineCurrency.add(rowInlineCurrencyUSD);
    rowsInlineCurrency.add(rowInlineCurrencyEUR);
    rowsInlineCurrency.add(rowInlineCurrencyRUB);

    markupInline.setKeyboard(rowsInlineCurrency);
    new_message.setChatId(String.valueOf(chatId));
    new_message.setMessageId(messageId);

    new_message.setText(SEND_TEXT_CHECK_BANK);
    new_message.setReplyMarkup(markupInline);
    return new_message;
  }
}