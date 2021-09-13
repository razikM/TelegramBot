package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;

public class SettingsButton {

  public SendMessage sendSettingsMenu(long chatId) {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

    InlineKeyboardButton buttonPrecision = new InlineKeyboardButton();
    InlineKeyboardButton buttonBank = new InlineKeyboardButton();
    InlineKeyboardButton buttonCurrency = new InlineKeyboardButton();
    InlineKeyboardButton buttonTimeUpdate = new InlineKeyboardButton();

    buttonPrecision.setText(SETTINGS.getButtonName());
    buttonPrecision.setCallbackData("callback_precision");

    buttonBank.setText(BANK.getButtonName());
    buttonBank.setCallbackData("callback_bank");

    buttonCurrency.setText(CURRENCY.getButtonName());
    buttonCurrency.setCallbackData("callback_currency");

    buttonTimeUpdate.setText(TIME_UPDATE.getButtonName());
    buttonTimeUpdate.setCallbackData("callback_time_update");

    List<InlineKeyboardButton> rowInlinePrecision  = new ArrayList<>();
    rowInlinePrecision.add(buttonPrecision);

    List<InlineKeyboardButton> rowInlineBank = new ArrayList<>();
    rowInlineBank.add(buttonBank);

    List<InlineKeyboardButton> rowInlineCurrency = new ArrayList<>();
    rowInlineCurrency.add(buttonCurrency);

    List<InlineKeyboardButton> rowInlineTimeUpdate = new ArrayList<>();
    rowInlineTimeUpdate.add(buttonTimeUpdate);

    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
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