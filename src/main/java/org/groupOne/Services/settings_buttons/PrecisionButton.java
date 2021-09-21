package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enum.ButtonName.*;
import static org.groupOne.Services.button_enum.ButtonData.*;

public class PrecisionButton {

  InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
  InlineKeyboardButton buttonPrecisionTwo = new InlineKeyboardButton();
  InlineKeyboardButton buttonPrecisionThree = new InlineKeyboardButton();
  InlineKeyboardButton buttonPrecisionFour = new InlineKeyboardButton();
  List<InlineKeyboardButton> rowInlinePrecisionTwo  = new ArrayList<>();
  List<InlineKeyboardButton> rowInlinePrecisionThree = new ArrayList<>();
  List<InlineKeyboardButton> rowInlinePrecisionFour = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowsInlinePrecision = new ArrayList<>();
  SendMessage message = new SendMessage();

  public SendMessage sendPrecisionInlineButtons(long chatId) {

    List<Settings> listSettings = ApplicationSettings.settingsList.stream()
        .filter(t -> t.getChatId().equals(chatId))
        .collect(Collectors.toList());
    Settings settings;

    if(listSettings.isEmpty()){
      settings = new Settings(chatId);
      ApplicationSettings.settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }

    if (settings.getPrecision() == Integer.parseInt(PRECISION_TWO.getButtonName())) {
      buttonPrecisionTwo.setText(PRECISION_TWO_CHECKED.getButtonName());
    } else {
      buttonPrecisionTwo.setText(PRECISION_TWO.getButtonName());
    }
    buttonPrecisionTwo.setCallbackData(PRECISION_TWO_DATA.getData());
    if (settings.getPrecision() == Integer.parseInt(PRECISION_THREE.getButtonName())) {
      buttonPrecisionThree.setText(PRECISION_THREE_CHECKED.getButtonName());
    } else {
      buttonPrecisionThree.setText(PRECISION_THREE.getButtonName());
    }
    buttonPrecisionThree.setCallbackData(PRECISION_THREE_DATA.getData());
    if (settings.getPrecision() == Integer.parseInt(PRECISION_FOUR.getButtonName())) {
      buttonPrecisionFour.setText(PRECISION_FOUR_CHECKED.getButtonName());
    } else {
      buttonPrecisionFour.setText(PRECISION_FOUR.getButtonName());
    }
    buttonPrecisionFour.setCallbackData(PRECISION_FOUR_DATA.getData());

    rowInlinePrecisionTwo.add(buttonPrecisionTwo);
    rowInlinePrecisionThree.add(buttonPrecisionThree);
    rowInlinePrecisionFour.add(buttonPrecisionFour);

    rowsInlinePrecision.add(rowInlinePrecisionTwo);
    rowsInlinePrecision.add(rowInlinePrecisionThree);
    rowsInlinePrecision.add(rowInlinePrecisionFour);

    markupInline.setKeyboard(rowsInlinePrecision);

    message.setChatId(String.valueOf(chatId));
    message.setText("\uD83C\uDFAF Выберите количество знаков после запятой:");
    message.setReplyMarkup(markupInline);

    return message;
  }
}