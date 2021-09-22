package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.GetMessageInfo;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enum.ButtonName.*;
import static org.groupOne.Services.button_enum.ButtonData.*;

public class InfoButton {

  static final Logger log = Logger.getLogger(InfoButton.class);
  InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
  InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
  InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
  List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
  List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
  SendMessage message = new SendMessage();
  static List<Settings> settingsList = new ArrayList<Settings>();
  GetMessageInfo getMessageInfo = new GetMessageInfo();

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
    log.info("InfoButton_USD = " + settings.isCheckUSD());
    log.info("InfoButton_EUR = " + settings.isCheckEUR());
    log.info("InfoButton_RUB = " + settings.isCheckRUB());

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
    if(settings.isCheckNBU()){
      message.setText(getMessageInfo.getMessageInfo(NBU,settings));
    }else if(settings.isCheckMonoBank()){
      message.setText(getMessageInfo.getMessageInfo(MONO_BANK,settings));
    }else if(settings.isCheckPrivatBank()){
      message.setText(getMessageInfo.getMessageInfo(PRIVAT_BANK,settings));
    }

    message.setReplyMarkup(inlineKeyboardMarkup);
    return message;
  }
}