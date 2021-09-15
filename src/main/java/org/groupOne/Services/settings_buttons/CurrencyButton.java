package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class CurrencyButton {

  public SendMessage sendCurrencyInlineButtons(Long chatId) {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    InlineKeyboardButton buttonCurrencyUSD = new InlineKeyboardButton();
    InlineKeyboardButton buttonCurrencyEUR = new InlineKeyboardButton();
    InlineKeyboardButton buttonCurrencyRUB = new InlineKeyboardButton();

    buttonCurrencyUSD.setText(USD.getButtonName());
    buttonCurrencyUSD.setCallbackData(USD_DATA.getData());

    buttonCurrencyEUR.setText(EUR.getButtonName());
    buttonCurrencyEUR.setCallbackData(EUR_DATA.getData());

    buttonCurrencyRUB.setText(RUR.getButtonName());
    buttonCurrencyRUB.setCallbackData(RUR_DATA.getData());


    List<InlineKeyboardButton> rowInlineCurrencyUSD  = new ArrayList<>();
    rowInlineCurrencyUSD.add(buttonCurrencyUSD);

    List<InlineKeyboardButton> rowInlineCurrencyEUR = new ArrayList<>();
    rowInlineCurrencyEUR.add(buttonCurrencyEUR);

    List<InlineKeyboardButton> rowInlineCurrencyRUB = new ArrayList<>();
    rowInlineCurrencyRUB.add(buttonCurrencyRUB);


    List<List<InlineKeyboardButton>> rowsInlineCurrency = new ArrayList<>();
    rowsInlineCurrency.add(rowInlineCurrencyUSD);
    rowsInlineCurrency.add(rowInlineCurrencyEUR);
    rowsInlineCurrency.add(rowInlineCurrencyRUB);

    markupInline.setKeyboard(rowsInlineCurrency);

    SendMessage message = new SendMessage();
    message.setChatId(String.valueOf(chatId));
    message.setText("\u200B\uD83D\uDCB4\u200B\uD83D\uDCB1\u200B\uD83D\uDCB5\u200B Выберите валюту:");
    message.setReplyMarkup(markupInline);

    return message;
  }
}