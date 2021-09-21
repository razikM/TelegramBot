package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.groupOne.ApplicationSettings;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enum.ButtonName.*;
import static org.groupOne.Services.button_enum.ButtonData.*;

public class BankButton {

  static final Logger log = Logger.getLogger(BankButton.class);
  InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
  InlineKeyboardButton buttonBankNbu = new InlineKeyboardButton();
  InlineKeyboardButton buttonBankPrivat = new InlineKeyboardButton();
  InlineKeyboardButton buttonBankMono = new InlineKeyboardButton();
  List<InlineKeyboardButton> rowInlineBankNbu  = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineBankPrivat = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineBankMono = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowsInlineBank = new ArrayList<>();
  SendMessage message = new SendMessage();

  public SendMessage sendBankInlineButtons(long chatId) {

    List<Settings> listSettings = ApplicationSettings.settingsList.stream()
        .filter(t -> t.getChatId().equals(chatId))
        .collect(Collectors.toList());
    Settings settings;

    if (listSettings.isEmpty()) {
      settings = new Settings(chatId);
      ApplicationSettings.settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }

    if (settings.isCheckNBU()) {
      buttonBankNbu.setText(NBU_CHECKED.getButtonName());
      buttonBankNbu.setCallbackData(NBU_DATA.getData());
    } else {
      buttonBankNbu.setText(NBU.getButtonName());
      buttonBankNbu.setCallbackData(NBU_DATA.getData());
    }
    if (settings.isCheckPrivatBank()) {
      buttonBankPrivat.setText(PRIVAT_BANK_CHECKED.getButtonName());
      buttonBankPrivat.setCallbackData(PRIVAT_BANK_DATA.getData());
    } else {
      buttonBankPrivat.setText(PRIVAT_BANK.getButtonName());
      buttonBankPrivat.setCallbackData(PRIVAT_BANK_DATA.getData());
    }
    if (settings.isCheckMonoBank()) {
        buttonBankMono.setText(MONO_BANK_CHECKED.getButtonName());
        buttonBankMono.setCallbackData(MONO_BANK_DATA.getData());
    } else {
      buttonBankMono.setText(MONO_BANK.getButtonName());
      buttonBankMono.setCallbackData(MONO_BANK_DATA.getData());
    }

    rowInlineBankNbu.add(buttonBankNbu);
    rowInlineBankPrivat.add(buttonBankPrivat);
    rowInlineBankMono.add(buttonBankMono);

    rowsInlineBank.add(rowInlineBankNbu);
    rowsInlineBank.add(rowInlineBankPrivat);
    rowsInlineBank.add(rowInlineBankMono);

    markupInline.setKeyboard(rowsInlineBank);

    message.setChatId(String.valueOf(chatId));
    message.setText("\uD83C\uDFE6  Выберите банк:");
    message.setReplyMarkup(markupInline);
    log.info("Bunk_button_message = " + message);
    return message;
  }
}