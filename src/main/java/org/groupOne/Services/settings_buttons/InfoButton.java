package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.groupOne.Services.Button;
import org.groupOne.Services.SendMessageBot;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;

public class InfoButton  implements Button {

  private SendMessageBot sendMessageBot;
  private final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
  private final List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
  private final List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
  private final List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

  public InfoButton(SendMessageBot sendMessageBot) {
    this.sendMessageBot = sendMessageBot;
  }
  public InfoButton() {}

  @Override
  public void execute(Update update, Settings settings) {
    String chatId = update.getMessage().getChatId().toString();
    String INFO_SEND_MESSAGE = "Курс в ПриватБанке: USD/UAH\nПокупка: 27.55\nПродажа: 27.95";
    sendMessageBot.SendMessage(chatId, INFO_SEND_MESSAGE, sendInfoMenu());
  }

  public ReplyKeyboard sendInfoMenu() {

    keyboardButtonsRow1.add(InlineKeyboardButton.builder().text(INFO.getButtonName()).callbackData(INFO.getButtonName()).build());
    keyboardButtonsRow2.add(InlineKeyboardButton.builder().text(SETTINGS.getButtonName()).callbackData(SETTINGS.getButtonName()).build());
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
    inlineKeyboardButton1.setCallbackData("get_info");
    inlineKeyboardButton2.setText(SETTINGS.getButtonName());
    inlineKeyboardButton2.setCallbackData("get_settings");

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