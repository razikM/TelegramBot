package org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_currency;

import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class EurButtonCheck extends CurrencyCheck {

  final InlineKeyboardButton buttonBankEUR = new InlineKeyboardButton();

  public InlineKeyboardButton sendEurBankButtonCheck(boolean check) {

    if (check) {
      buttonBankEUR.setText(EUR_CHECKED.getButtonName());
    }
    else {
      buttonBankEUR.setText(EUR.getButtonName());
    }
    buttonBankEUR.setCallbackData(EUR_DATA.getData());

    return buttonBankEUR;
  }
}