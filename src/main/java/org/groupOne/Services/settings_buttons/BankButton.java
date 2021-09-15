package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class BankButton {

  public SendMessage sendBankInlineButtons(Long chatId) {

    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    InlineKeyboardButton buttonBankNbu = new InlineKeyboardButton();
    InlineKeyboardButton buttonBankPrivat = new InlineKeyboardButton();
    InlineKeyboardButton buttonBankMono = new InlineKeyboardButton();

    buttonBankNbu.setText(NBU.getButtonName());
    buttonBankNbu.setCallbackData(NBU_DATA.getData());

    buttonBankPrivat.setText(PRIVAT_BANK.getButtonName());
    buttonBankPrivat.setCallbackData(PRIVAT_BANK_DATA.getData());

    buttonBankMono.setText(MONO_BANK.getButtonName());
    buttonBankMono.setCallbackData(MONO_BANK_DATA.getData());


    List<InlineKeyboardButton> rowInlineBankNbu  = new ArrayList<>();
    rowInlineBankNbu.add(buttonBankNbu);

    List<InlineKeyboardButton> rowInlineBankPrivat = new ArrayList<>();
    rowInlineBankPrivat.add(buttonBankPrivat);

    List<InlineKeyboardButton> rowInlineBankMono = new ArrayList<>();
    rowInlineBankMono.add(buttonBankMono);


    List<List<InlineKeyboardButton>> rowsInlineBank = new ArrayList<>();
    rowsInlineBank.add(rowInlineBankNbu);
    rowsInlineBank.add(rowInlineBankPrivat);
    rowsInlineBank.add(rowInlineBankMono);

    markupInline.setKeyboard(rowsInlineBank);

    SendMessage message = new SendMessage();
    message.setChatId(String.valueOf(chatId));
    message.setText("\uD83C\uDFE6  Выберите банк:");
    message.setReplyMarkup(markupInline);

    return message;
  }
}