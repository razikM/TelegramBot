package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class TimeUpdateButton {

  public final static String TIME_UPDATE_MESSAGE = "Выберите время оповещений: ";
  ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
  KeyboardRow keyboardFirstRow = new KeyboardRow();
  KeyboardRow keyboardSecondRow = new KeyboardRow();
  KeyboardRow keyboardThirdRow = new KeyboardRow();
  KeyboardRow keyboardForthRow = new KeyboardRow();
  SendMessage message = new SendMessage();

  public SendMessage sendTimeUpdateMenu(long chatId) {

    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);
    List<KeyboardRow> keyboard = new ArrayList<>();

    keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_NINE.getButtonName()));
    keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_THEN.getButtonName()));
    keyboardFirstRow.add(new KeyboardButton(TIME_UPDATE_ELEVEN.getButtonName()));

    keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_TWELVE.getButtonName()));
    keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_THIRTEEN.getButtonName()));
    keyboardSecondRow.add(new KeyboardButton(TIME_UPDATE_FOURTEEN.getButtonName()));

    keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_FIFTEEN.getButtonName()));
    keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_SIXTEEN.getButtonName()));
    keyboardThirdRow.add(new KeyboardButton(TIME_UPDATE_SEVENTEEN.getButtonName()));

    keyboardForthRow.add(new KeyboardButton(TIME_UPDATE_EIGHTEEN.getButtonName()));
    keyboardForthRow.add(new KeyboardButton(TIME_UPDATE_DISABLE.getButtonName()));

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