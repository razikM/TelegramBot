package org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_currency;

import static org.groupOne.Services.button_enum.ButtonName.*;
import static org.groupOne.Services.button_enum.ButtonData.*;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class RubButtonCheck extends CurrencyCheck {

  final InlineKeyboardButton buttonBankRUB = new InlineKeyboardButton();

  public InlineKeyboardButton sendRubButtonCheck(boolean check) {

    if (check) {
      buttonBankRUB.setText(RUB_CHECKED.getButtonName());
    }
    else {
      buttonBankRUB.setText(RUB.getButtonName());
    }
    buttonBankRUB.setCallbackData(RUB_DATA.getData());

    return buttonBankRUB;
  }
}