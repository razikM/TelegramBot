package org.groupOne.Services.settings_buttons.check_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import static org.groupOne.Services.button_enam.ButtonName.*;

public class TimeUpdateCheck {

    public static String TIME_UPDATE_MESSAGE = "";// = //"Выберите время оповещений: ";
    static ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    static  Settings settings;

    static SendMessage message = new SendMessage();

  public static void clearTimeUpdate(Settings settings) {

    KeyboardButton.builder().text(TIME_UPDATE_NINE.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_TEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_ELEVEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_TWELVE.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_THIRTEEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_FOURTEEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_FIFTEEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_SIXTEEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_SEVENTEEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_EIGHTEEN.getButtonName()).build();
    KeyboardButton.builder().text(TIME_UPDATE_ENABLE.getButtonName()).build();

    settings.setTimeUpdate(0);
    settings.setCheckDisableTimeUpdate(false);
  }

    public static SendMessage sendTimeUpdateMenu(long chatId, String data) {

      List<Settings> listSettings = ApplicationSettings.settingsList.stream()
          .filter(t -> t.getChatId().equals(chatId)).collect(
              Collectors.toList());
//      Settings settings;

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

      clearTimeUpdate(settings);

      if (data.equals(TIME_UPDATE_NINE.getButtonName())) {
        keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_NINE_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(9);
        TIME_UPDATE_MESSAGE = "время оповещений в 9:00";
      }
      else {
        keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_NINE.getButtonName()).build());
      }

      if (data.equals(TIME_UPDATE_TEN.getButtonName())) {
        keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_TEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(10);
        TIME_UPDATE_MESSAGE = "время оповещений в 10:00";
      } else {
        keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_TEN.getButtonName()).build());
      }

      if (data.equals(TIME_UPDATE_ELEVEN.getButtonName())) {
        keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_ELEVEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(11);
        TIME_UPDATE_MESSAGE = "время оповещений в 11:00";
      } else {
        keyboardFirstRow.add(KeyboardButton.builder().text(TIME_UPDATE_ELEVEN.getButtonName()).build());
      }

      if (data.equals(TIME_UPDATE_TWELVE.getButtonName())) {
        keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_TWELVE_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(12);
        TIME_UPDATE_MESSAGE = "время оповещений в 12:00";
      } else {
        keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_TWELVE.getButtonName()).build());
      }

      if (data.equals(TIME_UPDATE_THIRTEEN.getButtonName())) {
        keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_THIRTEEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(13);
        TIME_UPDATE_MESSAGE = "время оповещений в 13:00";
      } else {
        keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_THIRTEEN.getButtonName()).build());
      }

    if (data.equals(TIME_UPDATE_FOURTEEN.getButtonName())) {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_FOURTEEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(14);
        TIME_UPDATE_MESSAGE = "время оповещений в 14:00";
      } else {
      keyboardSecondRow.add(KeyboardButton.builder().text(TIME_UPDATE_FOURTEEN.getButtonName()).build());
      }
    if (data.equals(TIME_UPDATE_FIFTEEN.getButtonName())) {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_FIFTEEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(15);
        TIME_UPDATE_MESSAGE = "время оповещений в 15:00";
      } else {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_FIFTEEN.getButtonName()).build());
      }

    if (data.equals(TIME_UPDATE_SIXTEEN.getButtonName())) {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SIXTEEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(16);
        TIME_UPDATE_MESSAGE = "время оповещений в 16:00";
      } else {
      keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SIXTEEN.getButtonName()).build());
      }

      if (data.equals(TIME_UPDATE_SEVENTEEN.getButtonName())) {
        keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SEVENTEEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(17);
        TIME_UPDATE_MESSAGE = "время оповещений в 17:00";
      } else {
        keyboardThirdRow.add(KeyboardButton.builder().text(TIME_UPDATE_SEVENTEEN.getButtonName()).build());
      }

      if (data.equals(TIME_UPDATE_EIGHTEEN.getButtonName())) {
        keyboardForthRow.add(KeyboardButton.builder().text(TIME_UPDATE_EIGHTEEN_CHOOSE.getButtonName()).build());
        settings.setTimeUpdate(18);
        TIME_UPDATE_MESSAGE = "время оповещений в 18:00";
      } else {
        keyboardForthRow.add(KeyboardButton.builder().text(TIME_UPDATE_EIGHTEEN.getButtonName()).build());
      }

      if (data.equals(TIME_UPDATE_DISABLE.getButtonName())) {
        keyboardForthRow.add(new DisableTimeUpdate().sendTimeUpdateMenu(true));
        settings.setCheckDisableTimeUpdate(true);
        TIME_UPDATE_MESSAGE = "Уведомления включены";
      } else if (data.equals(TIME_UPDATE_ENABLE.getButtonName())) {
        keyboardForthRow.add(new DisableTimeUpdate().sendTimeUpdateMenu(false));
        settings.setCheckDisableTimeUpdate(false);
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