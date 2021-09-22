package org.groupOne.Services.settings_buttons.check_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enum.ButtonName.*;
import static org.groupOne.Services.button_enum.ButtonData.*;

public class PrecisionCheck {

  InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
  InlineKeyboardButton buttonPrecisionTwo = new InlineKeyboardButton();
  InlineKeyboardButton buttonPrecisionThree = new InlineKeyboardButton();
  InlineKeyboardButton buttonPrecisionFour = new InlineKeyboardButton();
  List<InlineKeyboardButton> rowInlinePrecisionTwo  = new ArrayList<>();
  List<InlineKeyboardButton> rowInlinePrecisionThree = new ArrayList<>();
  List<InlineKeyboardButton> rowInlinePrecisionFour = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowsInlinePrecision = new ArrayList<>();
  EditMessageText new_message = new EditMessageText();

  public void clearPrecision (Settings settings) {

    buttonPrecisionTwo.setText(PRECISION_TWO.getButtonName());
    buttonPrecisionTwo.setCallbackData(PRECISION_TWO_DATA.getData());

    buttonPrecisionThree.setText(PRECISION_THREE.getButtonName());
    buttonPrecisionThree.setCallbackData(PRECISION_THREE_DATA.getData());

    buttonPrecisionFour.setText(PRECISION_FOUR.getButtonName());
    buttonPrecisionFour.setCallbackData(PRECISION_FOUR_DATA.getData());

    settings.setPrecision(0);
  }

  public EditMessageText sendPrecisionInlineButtonsChecked(long chatId, String data, Integer messageId) {

    List<Settings> listSettings = ApplicationSettings.settingsList.stream().filter(t -> t.getChatId().equals(chatId)).collect(
        Collectors.toList());
    Settings settings;

    if (listSettings.isEmpty()) {
      settings = new Settings(chatId);
      ApplicationSettings.settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }

    clearPrecision(settings);

    if (data.equals(PRECISION_TWO_DATA.getData())) {
      buttonPrecisionTwo.setText(PRECISION_TWO_CHECKED.getButtonName());
      settings.setPrecision(2);
    } else if (data.equals(PRECISION_THREE_DATA.getData())) {
      buttonPrecisionThree.setText(PRECISION_THREE_CHECKED.getButtonName());
      settings.setPrecision(3);
    } else if (data.equals(PRECISION_FOUR_DATA.getData())) {
      buttonPrecisionFour.setText(PRECISION_FOUR_CHECKED.getButtonName());
      settings.setPrecision(4);
    }

    rowInlinePrecisionTwo.add(buttonPrecisionTwo);
    rowInlinePrecisionThree.add(buttonPrecisionThree);
    rowInlinePrecisionFour.add(buttonPrecisionFour);

    rowsInlinePrecision.add(rowInlinePrecisionTwo);
    rowsInlinePrecision.add(rowInlinePrecisionThree);
    rowsInlinePrecision.add(rowInlinePrecisionFour);
    markupInline.setKeyboard(rowsInlinePrecision);

    new_message.setChatId(String.valueOf(chatId));
    new_message.setMessageId(messageId);
    new_message.setText("\uD83C\uDFAF Выберите количество знаков после запятой:");
    new_message.setReplyMarkup(markupInline);

    return new_message;
  }
}