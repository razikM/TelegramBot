package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class InfoButton {

  InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
  InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
  InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
  List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
  List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
  SendMessage message = new SendMessage();

  public SendMessage sendInfoMenu(long chatId) {

    List<Settings> listSettings = ApplicationSettings.settingsList.stream().filter(t -> t.getChatId().equals(chatId)).collect(
        Collectors.toList());
    Settings settings;

    if (listSettings.isEmpty()) {
      settings = new Settings(chatId);
      ApplicationSettings.settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }

    inlineKeyboardButton1.setText(INFO.getButtonName());
    inlineKeyboardButton1.setCallbackData(INFO_DATA.getData());
    inlineKeyboardButton2.setText(SETTINGS.getButtonName());
    inlineKeyboardButton2.setCallbackData(SETTINGS_DATA.getData());

    keyboardButtonsRow1.add(inlineKeyboardButton1);
    keyboardButtonsRow2.add(inlineKeyboardButton2);

    rowList.add(keyboardButtonsRow1);
    rowList.add(keyboardButtonsRow2);
    inlineKeyboardMarkup.setKeyboard(rowList);

    message.setChatId(String.valueOf(chatId));
    message.setText("Курс в ПриватБанке: USD/UAH\nПокупка: 27.55\nПродажа: 27.95");
    message.setReplyMarkup(inlineKeyboardMarkup);
    return message;
  }
}