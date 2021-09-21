package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import static org.groupOne.Services.button_enum.ButtonName.*;

public class TimeUpdateButton {

  public final static String TIME_UPDATE_MESSAGE = "Выберите время оповещений: ";
  ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
  KeyboardRow keyboardFirstRow = new KeyboardRow();
  KeyboardRow keyboardSecondRow = new KeyboardRow();
  KeyboardRow keyboardThirdRow = new KeyboardRow();
  KeyboardRow keyboardForthRow = new KeyboardRow();
  SendMessage message = new SendMessage();

  public SendMessage sendTimeUpdateMenu(long chatId) {

    List<Settings> listSettings = ApplicationSettings.settingsList.stream()
        .filter(t -> t.getChatId().equals(chatId)).collect(
            Collectors.toList());
    Settings settings;

    if (listSettings.isEmpty()) {
      settings = new Settings(chatId);
      ApplicationSettings.settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }

    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);
    List<KeyboardRow> keyboard = new ArrayList<>();

    if (settings.getTimeUpdate() == 9) {
      keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_NINE_CHOOSE.getButtonName()));

    } else {
      keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_NINE.getButtonName()));
    }
    if (settings.getPrecision() == 10) {
      keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_TEN_CHOOSE.getButtonName()));
  } else {
      keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_TEN.getButtonName()));
    }
    if (settings.getPrecision() == 11) {
      keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_ELEVEN_CHOOSE.getButtonName()));
    } else {
      keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_ELEVEN.getButtonName()));
    }


    if (settings.getTimeUpdate() == 12) {
      keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_TWELVE_CHOOSE.getButtonName()));
    } else {
      keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_TWELVE.getButtonName()));
    }
    if (settings.getTimeUpdate() == 13) {
      keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_THIRTEEN_CHOOSE.getButtonName()));
    } else {
      keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_THIRTEEN.getButtonName()));
    }
    if (settings.getTimeUpdate() == 14) {
      keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_FOURTEEN_CHOOSE.getButtonName()));
    } else {
      keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_FOURTEEN.getButtonName()));
    }


    if (settings.getTimeUpdate() == 15) {
      keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_FIFTEEN_CHOOSE.getButtonName()));
    } else {
      keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_FIFTEEN.getButtonName()));
    }
    if (settings.getTimeUpdate() == 16) {
      keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_SIXTEEN_CHOOSE.getButtonName()));
    } else {
      keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_SIXTEEN.getButtonName()));
    }
    if (settings.getTimeUpdate() == 17) {
      keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_SEVENTEEN_CHOOSE.getButtonName()));
    } else {
      keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_SEVENTEEN.getButtonName()));
    }


    if (settings.getTimeUpdate() == 18) {
      keyboardForthRow.add(new KeyboardButton(TIME_UPDATE_EIGHTEEN_CHOOSE.getButtonName()));
    } else {
      keyboardForthRow.add(new KeyboardButton(TIME_UPDATE_EIGHTEEN.getButtonName()));
    }
    if (settings.isCheckDisableTimeUpdate()) {
      keyboardForthRow.add(new KeyboardButton(TIME_UPDATE_ENABLE.getButtonName()));
    } else {
      keyboardForthRow.add(new KeyboardButton(TIME_UPDATE_DISABLE.getButtonName()));
    }

    keyboard.add(keyboardFirstRow);
    keyboard.add(keyboardSecondRow);
    keyboard.add(keyboardThirdRow);
    keyboard.add(keyboardForthRow);
    replyKeyboardMarkup.setKeyboard(keyboard);

    message.setChatId(String.valueOf(chatId));
    message.setText(TIME_UPDATE_MESSAGE);
    message.setReplyMarkup(replyKeyboardMarkup);
    return message;
  }
}