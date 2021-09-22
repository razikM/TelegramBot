package org.groupOne.Services.settings_buttons.check_buttons;

import static org.groupOne.Services.button_enum.ButtonName.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class DisableTimeUpdateCheck {

  public static String TIME_UPDATE_MESSAGE = "";
  static ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
  static  Settings settings;
  static SendMessage message = new SendMessage();

  public static SendMessage sendTimeUpdateDisable(long chatId, String data) {

    List<Settings> listSettings = ApplicationSettings.settingsList.stream()
        .filter(t -> t.getChatId().equals(chatId)).collect(
            Collectors.toList());

    if (listSettings.isEmpty()) {
      settings = new Settings(chatId);
      ApplicationSettings.settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }


    KeyboardRow keyboardFirstRow = new KeyboardRow();
    KeyboardRow keyboardSecondRow = new KeyboardRow();
    KeyboardRow keyboardThirdRow = new KeyboardRow();
    KeyboardRow keyboardForthRow = new KeyboardRow();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);
    List<KeyboardRow> keyboard = new ArrayList<>();

    if (settings.getTimeUpdate() == 9) {
      keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_NINE_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 9:00";
    }
    else {
      keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_NINE.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 10) {
      keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_TEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 10:00";
    } else {
      keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_TEN.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 11) {
      keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_ELEVEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 11:00";
    } else {
      keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_ELEVEN.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 12) {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_TWELVE_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 12:00";
    } else {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_TWELVE.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 13) {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_THIRTEEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 13:00";
    } else {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_THIRTEEN.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 14) {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_FOURTEEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 14:00";
    } else {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_FOURTEEN.getButtonName()).build());
    }
    if (settings.getTimeUpdate() == 15) {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_FIFTEEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 15:00";
    } else {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_FIFTEEN.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 16) {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SIXTEEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 16:00";
    } else {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SIXTEEN.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 17) {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SEVENTEEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 17:00";
    } else {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SEVENTEEN.getButtonName()).build());
    }

    if (settings.getTimeUpdate() == 18) {
      keyboardForthRow.add(KeyboardButton.builder().text(TIME_UPDATE_EIGHTEEN_CHOOSE.getButtonName()).build());
      TIME_UPDATE_MESSAGE = "время оповещений в 18:00";
    } else {
      keyboardForthRow.add(KeyboardButton.builder().text(TIME_UPDATE_EIGHTEEN.getButtonName()).build());
    }

    if (data.equals(TIME_UPDATE_ENABLE.getButtonName())) {
      keyboardForthRow.add(new DisableTimeUpdate().sendTimeUpdateMenu(!settings.isCheckDisableTimeUpdate()));
      settings.setCheckDisableTimeUpdate(!settings.isCheckDisableTimeUpdate());
      TIME_UPDATE_MESSAGE = "Уведомления включены";
    } else if (data.equals(TIME_UPDATE_DISABLE.getButtonName())) {
      keyboardForthRow.add(new DisableTimeUpdate().sendTimeUpdateMenu(!settings.isCheckDisableTimeUpdate()));
      settings.setCheckDisableTimeUpdate(!settings.isCheckDisableTimeUpdate());
      TIME_UPDATE_MESSAGE = "Уведомления выключены";
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