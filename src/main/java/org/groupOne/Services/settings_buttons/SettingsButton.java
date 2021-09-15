package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class SettingsButton {



  public ReplyKeyboard sendSettingsMenu() {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

    InlineKeyboardButton buttonPrecision = new InlineKeyboardButton();
    InlineKeyboardButton buttonBank = new InlineKeyboardButton();
    InlineKeyboardButton buttonCurrency = new InlineKeyboardButton();
    InlineKeyboardButton buttonTimeUpdate = new InlineKeyboardButton();

    buttonPrecision.setText(PRECISION.getButtonName());
    buttonPrecision.setCallbackData(PRECISION_DATA.getData());

    buttonBank.setText(BANK.getButtonName());
    buttonBank.setCallbackData(BANK_DATA.getData());

    buttonCurrency.setText(CURRENCY.getButtonName());
    buttonCurrency.setCallbackData(CURRENCY_DATA.getData());

    buttonTimeUpdate.setText(TIME_UPDATE.getButtonName());
    buttonTimeUpdate.setCallbackData(TIME_UPDATE_DATA.getData());

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

    return markupInline;
  }

  public static SendMessage sendSettingsMenu(Long chatId) {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

    InlineKeyboardButton buttonPrecision = new InlineKeyboardButton();
    InlineKeyboardButton buttonBank = new InlineKeyboardButton();
    InlineKeyboardButton buttonCurrency = new InlineKeyboardButton();
    InlineKeyboardButton buttonTimeUpdate = new InlineKeyboardButton();

    buttonPrecision.setText(PRECISION.getButtonName());
    buttonPrecision.setCallbackData(PRECISION_DATA.getData());

    buttonBank.setText(BANK.getButtonName());
    buttonBank.setCallbackData(BANK_DATA.getData());

    buttonCurrency.setText(CURRENCY.getButtonName());
    buttonCurrency.setCallbackData(CURRENCY_DATA.getData());

    buttonTimeUpdate.setText(TIME_UPDATE.getButtonName());
    buttonTimeUpdate.setCallbackData(TIME_UPDATE_DATA.getData());

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