package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enum.ButtonName.*;
import static org.groupOne.Services.button_enum.ButtonData.*;

  public class CurrencyButton {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    InlineKeyboardButton buttonCurrencyUSD = new InlineKeyboardButton();
    InlineKeyboardButton buttonCurrencyEUR = new InlineKeyboardButton();
    InlineKeyboardButton buttonCurrencyRUB = new InlineKeyboardButton();
    List<InlineKeyboardButton> rowInlineCurrencyUSD  = new ArrayList<>();
    List<InlineKeyboardButton> rowInlineCurrencyEUR = new ArrayList<>();
    List<InlineKeyboardButton> rowInlineCurrencyRUB = new ArrayList<>();
    List<List<InlineKeyboardButton>> rowsInlineCurrency = new ArrayList<>();
    SendMessage message = new SendMessage();

    public SendMessage sendCurrencyInlineButtons(long chatId) {

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

      if (settings.isCheckUSD()) {
        buttonCurrencyUSD.setText(USD_CHECKED.getButtonName());
        buttonCurrencyUSD.setCallbackData(USD_DATA.getData());
      } else {
        buttonCurrencyUSD.setText(USD.getButtonName());
        buttonCurrencyUSD.setCallbackData(USD_DATA.getData());
      }
      if (settings.isCheckEUR()) {
        buttonCurrencyEUR.setText(EUR_CHECKED.getButtonName());
        buttonCurrencyEUR.setCallbackData(EUR_DATA.getData());
      } else {
        buttonCurrencyEUR.setText(EUR.getButtonName());
        buttonCurrencyEUR.setCallbackData(EUR_DATA.getData());
      }
      if (settings.isCheckRUB()) {
        buttonCurrencyRUB.setText(RUB_CHECKED.getButtonName());
        buttonCurrencyRUB.setCallbackData(RUB_DATA.getData());
      } else {
        buttonCurrencyRUB.setText(RUB.getButtonName());
        buttonCurrencyRUB.setCallbackData(RUB_DATA.getData());
      }

      rowInlineCurrencyUSD.add(buttonCurrencyUSD);
      rowInlineCurrencyEUR.add(buttonCurrencyEUR);
      rowInlineCurrencyRUB.add(buttonCurrencyRUB);

      rowsInlineCurrency.add(rowInlineCurrencyUSD);
      rowsInlineCurrency.add(rowInlineCurrencyEUR);
      rowsInlineCurrency.add(rowInlineCurrencyRUB);

      markupInline.setKeyboard(rowsInlineCurrency);

      message.setChatId(String.valueOf(chatId));
      message.setText("\u200B\uD83D\uDCB4\u200B\uD83D\uDCB1\u200B\uD83D\uDCB5\u200B Выберите валюту: ");
      message.setReplyMarkup(markupInline);

      return message;
    }
  }