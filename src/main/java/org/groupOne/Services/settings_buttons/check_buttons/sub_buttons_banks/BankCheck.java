package org.groupOne.Services.settings_buttons.check_buttons.sub_buttons_banks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class BankCheck {

  static final Logger log = Logger.getLogger(BankCheck.class);
  final InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
  InlineKeyboardButton buttonBankNbu = new InlineKeyboardButton();
  InlineKeyboardButton buttonBankPrivat = new InlineKeyboardButton();
  InlineKeyboardButton buttonBankMono = new InlineKeyboardButton();
  List<InlineKeyboardButton> rowInlineBankNbu  = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineBankPrivat = new ArrayList<>();
  List<InlineKeyboardButton> rowInlineBankMono = new ArrayList<>();
  List<List<InlineKeyboardButton>> rowsInlineBank = new ArrayList<>();
  EditMessageText new_message = new EditMessageText();
  static List<Settings> settingsList = new ArrayList<Settings>();

  public void clearBank (Settings settings) {
    buttonBankNbu.setText(NBU.getButtonName());
    buttonBankNbu.setCallbackData(NBU_DATA.getData());
    settings.setCheckNBU(false);

    buttonBankPrivat.setText(PRIVAT_BANK.getButtonName());
    buttonBankPrivat.setCallbackData(PRIVAT_BANK_DATA.getData());
    settings.setCheckPrivatBank(false);

    buttonBankMono.setText(MONO_BANK.getButtonName());
    buttonBankMono.setCallbackData(MONO_BANK_DATA.getData());
    settings.setCheckMonoBank(false);
  }

  public EditMessageText sendBankInlineButtonsChecked(long chatId, String data, Integer messageId) {

    String SEND_TEXT_CHECK_BANK = "\uD83C\uDFE6  Выберите банк:";
    List<Settings> listSettings = settingsList.stream().filter(t -> t.getChatId().equals(chatId)).collect(
        Collectors.toList());
    Settings settings;

    if(listSettings.isEmpty()){
      settings = new Settings(chatId);
      settingsList.add(settings);
    } else {
      settings = listSettings.get(0);
    }

    clearBank(settings);

    if (data.equals(NBU_DATA.getData())) {
      buttonBankNbu.setText(NBU_CHECKED.getButtonName());
      settings.setCheckNBU(true);
    } else {
      buttonBankNbu.setText(NBU.getButtonName());
      settings.setCheckNBU(false);
    }
    if (data.equals(PRIVAT_BANK_DATA.getData())) {
      buttonBankPrivat.setText(PRIVAT_BANK_CHECKED.getButtonName());
      settings.setCheckPrivatBank(true);
    } else {
      buttonBankPrivat.setText(PRIVAT_BANK.getButtonName());
      settings.setCheckPrivatBank(false);
    }
    if (data.equals(MONO_BANK_DATA.getData())) {
      buttonBankMono.setText(MONO_BANK_CHECKED.getButtonName());
      settings.setCheckMonoBank(true);
    } else {
      buttonBankMono.setText(MONO_BANK.getButtonName());
      settings.setCheckMonoBank(false);
    }

    rowInlineBankNbu.add(buttonBankNbu);
    rowInlineBankPrivat.add(buttonBankPrivat);
    rowInlineBankMono.add(buttonBankMono);


    rowsInlineBank.add(rowInlineBankNbu);
    rowsInlineBank.add(rowInlineBankPrivat);
    rowsInlineBank.add(rowInlineBankMono);

    markupInline.setKeyboard(rowsInlineBank);
    new_message.setChatId(String.valueOf(chatId));
    new_message.setMessageId(messageId);

    new_message.setText(SEND_TEXT_CHECK_BANK);
    new_message.setReplyMarkup(markupInline);

    return new_message;
  }
}