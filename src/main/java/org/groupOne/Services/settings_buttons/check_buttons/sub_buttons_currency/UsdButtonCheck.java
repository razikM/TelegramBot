package org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_currency;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enum.ButtonName.*;
import static org.groupOne.Services.button_enum.ButtonData.*;

public class UsdButtonCheck extends CurrencyCheck {

  final InlineKeyboardButton buttonBankUSD = new InlineKeyboardButton();

  public InlineKeyboardButton sendUsdBankButtonCheck(boolean check) {

    if (check) {
      buttonBankUSD.setText(USD_CHECKED.getButtonName());
    }
    else {
      buttonBankUSD.setText(USD.getButtonName());
    }
    buttonBankUSD.setCallbackData(USD_DATA.getData());

    return buttonBankUSD;
  }
}