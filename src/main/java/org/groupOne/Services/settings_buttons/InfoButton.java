package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class InfoButton {

  private final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
  private final List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
  private final List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
  private final List<List<InlineKeyboardButton>> rowList = new ArrayList<>();


  public ReplyKeyboard sendInfoMenu() {

    keyboardButtonsRow1.add(InlineKeyboardButton.builder().text(INFO.getButtonName()).callbackData(INFO_DATA.getData()).build());
    keyboardButtonsRow2.add(InlineKeyboardButton.builder().text(SETTINGS.getButtonName()).callbackData(SETTINGS_DATA.getData()).build());
    rowList.add(keyboardButtonsRow1);
    rowList.add(keyboardButtonsRow2);
    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }

  public SendMessage sendInfoMenu(long chatId) {

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
    InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

    inlineKeyboardButton1.setText(INFO.getButtonName());
    inlineKeyboardButton1.setCallbackData(INFO_DATA.getData());
    inlineKeyboardButton2.setText(SETTINGS.getButtonName());
    inlineKeyboardButton2.setCallbackData(SETTINGS_DATA.getData());

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
    List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
    keyboardButtonsRow1.add(inlineKeyboardButton1);
    keyboardButtonsRow2.add(inlineKeyboardButton2);

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);
    rowList.add(keyboardButtonsRow2);
    inlineKeyboardMarkup.setKeyboard(rowList);
    SendMessage message = new SendMessage();
    message.setChatId(String.valueOf(chatId));
    message.setText("Курс в ПриватБанке: USD/UAH\nПокупка: 27.55\nПродажа: 27.95");
    message.setReplyMarkup(inlineKeyboardMarkup);
    return message;
  }

}