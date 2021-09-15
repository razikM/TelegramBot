package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class PrecisionButton {

  public SendMessage sendPrecisionInlineButtons(Long chatId) {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    InlineKeyboardButton buttonPrecisionTwo = new InlineKeyboardButton();
    InlineKeyboardButton buttonPrecisionThree = new InlineKeyboardButton();
    InlineKeyboardButton buttonPrecisionFour = new InlineKeyboardButton();

    buttonPrecisionTwo.setText(PRECISION_TWO.getButtonName());
    buttonPrecisionTwo.setCallbackData(PRECISION_TWO_DATA.getData());

    buttonPrecisionThree.setText(PRECISION_THREE.getButtonName());
    buttonPrecisionThree.setCallbackData(PRECISION_THREE_DATA.getData());

    buttonPrecisionFour.setText(PRECISION_FOUR.getButtonName());
    buttonPrecisionFour.setCallbackData(PRECISION_FOUR_DATA.getData());


    List<InlineKeyboardButton> rowInlinePrecisionTwo  = new ArrayList<>();
    rowInlinePrecisionTwo.add(buttonPrecisionTwo);

    List<InlineKeyboardButton> rowInlinePrecisionThree = new ArrayList<>();
    rowInlinePrecisionThree.add(buttonPrecisionThree);

    List<InlineKeyboardButton> rowInlinePrecisionFour = new ArrayList<>();
    rowInlinePrecisionFour.add(buttonPrecisionFour);


    List<List<InlineKeyboardButton>> rowsInlinePrecision = new ArrayList<>();
    rowsInlinePrecision.add(rowInlinePrecisionTwo);
    rowsInlinePrecision.add(rowInlinePrecisionThree);
    rowsInlinePrecision.add(rowInlinePrecisionFour);

    markupInline.setKeyboard(rowsInlinePrecision);

    SendMessage message = new SendMessage();
    message.setChatId(String.valueOf(chatId));
    message.setText("\uD83C\uDFAF Выберите количество знаков после запятой:");
    message.setReplyMarkup(markupInline);

    return message;
  }
}