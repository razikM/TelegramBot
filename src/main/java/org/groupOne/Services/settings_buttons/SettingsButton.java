package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class SettingsButton {

  InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
  InlineKeyboardButton buttonPrecision = new InlineKeyboardButton();
  InlineKeyboardButton buttonBank = new InlineKeyboardButton();
  InlineKeyboardButton buttonCurrency = new InlineKeyboardButton();
  InlineKeyboardButton buttonTimeUpdate = new InlineKeyboardButton();
  List<InlineKeyboardButton> rowInlinePrecision  = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineBank = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineCurrency = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineTimeUpdate = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

  public SendMessage sendSettingsMenu(long chatId) {

    buttonPrecision.setText(PRECISION.getButtonName());
    buttonPrecision.setCallbackData(PRECISION_DATA.getData());

    buttonBank.setText(BANK.getButtonName());
    buttonBank.setCallbackData(BANK_DATA.getData());

    buttonCurrency.setText(CURRENCY.getButtonName());
    buttonCurrency.setCallbackData(CURRENCY_DATA.getData());

    buttonTimeUpdate.setText(TIME_UPDATE.getButtonName());
    buttonTimeUpdate.setCallbackData(TIME_UPDATE_DATA.getData());

    rowInlinePrecision.add(buttonPrecision);
    rowInlineBank.add(buttonBank);
    rowInlineCurrency.add(buttonCurrency);
    rowInlineTimeUpdate.add(buttonTimeUpdate);


    rowsInline.add(rowInlinePrecision);
    rowsInline.add(rowInlineBank);
    rowsInline.add(rowInlineCurrency);
    rowsInline.add(rowInlineTimeUpdate);

    markupInline.setKeyboard(rowsInline);
    SendMessage message = new SendMessage();
    message.setChatId(String.valueOf(chatId));
    message.setText("Настройки");
    message.setReplyMarkup(markupInline);
    return message;
  }
}