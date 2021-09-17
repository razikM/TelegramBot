package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;
public class StartButton {

  private static final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
  private static final InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
  private static final InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
  private static final List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
  private static final List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
  private static final List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
  private static final SendMessage message = new SendMessage();

  public static SendMessage sendStartMenu(long chatId, Update update) {

    String firstname = update.getMessage().getFrom().getFirstName();
    String START_MESSAGE = "Привет, " + firstname + "\uD83D\uDC4B. Этот бот поможет отслеживать актуальные курсы валют";

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
    message.setText(START_MESSAGE);
    message.setReplyMarkup(inlineKeyboardMarkup);
    return message;
  }
}