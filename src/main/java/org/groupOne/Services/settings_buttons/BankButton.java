package org.groupOne.Services.settings_buttons;

import java.util.ArrayList;
import java.util.List;

import org.groupOne.Services.Settings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import static org.groupOne.Services.button_enam.ButtonName.*;
import static org.groupOne.Services.button_enam.ButtonData.*;

public class BankButton {
    private InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    private InlineKeyboardButton buttonBankNbu = new InlineKeyboardButton();
    private InlineKeyboardButton buttonBankPrivat = new InlineKeyboardButton();
    private InlineKeyboardButton buttonBankMono = new InlineKeyboardButton();
    private List<InlineKeyboardButton> rowInlineBankNbu = new ArrayList<>();
    private List<InlineKeyboardButton> rowInlineBankPrivat = new ArrayList<>();
    private List<InlineKeyboardButton> rowInlineBankMono = new ArrayList<>();
    private List<List<InlineKeyboardButton>> rowsInlineBank = new ArrayList<>();
    private final static String CHECK = "✅ ";
    Settings settings;

    public BankButton() {
        rowInlineBankNbu.add(buttonBankNbu);
        rowInlineBankPrivat.add(buttonBankPrivat);
        rowInlineBankMono.add(buttonBankMono);

        rowsInlineBank.add(rowInlineBankNbu);
        rowsInlineBank.add(rowInlineBankPrivat);
        rowsInlineBank.add(rowInlineBankMono);
        buttonBankNbu.setText(NBU.getButtonName());
        buttonBankPrivat.setText(PRIVAT_BANK.getButtonName());
        buttonBankMono.setText(MONO_BANK.getButtonName());

        buttonBankNbu.setCallbackData(NBU_DATA.getData());
        buttonBankPrivat.setCallbackData(PRIVAT_BANK_DATA.getData());
        buttonBankMono.setCallbackData(MONO_BANK_DATA.getData());
        settings = new Settings(123L);
    }

    public SendMessage sendBankInlineButtons(Long chatId) {


        if (settings.isCheckNBU()) {
            buttonBankNbu.setText(CHECK + NBU.getButtonName());
        }
        if (settings.isCheckPrivatBank()) {
            buttonBankPrivat.setText(CHECK + PRIVAT_BANK.getButtonName());

        }
        if (settings.isCheckMonoBank()) {
            buttonBankMono.setText(CHECK + MONO_BANK.getButtonName());

        }
        buttonBankNbu.setCallbackData(NBU_DATA.getData());
        buttonBankPrivat.setCallbackData(PRIVAT_BANK_DATA.getData());
        buttonBankMono.setCallbackData(MONO_BANK_DATA.getData());

        markupInline.setKeyboard(rowsInlineBank);

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("\uD83C\uDFE6  Выберите банк:");
        message.setReplyMarkup(markupInline);

        return message;
    }

    public EditMessageText ediMessage(Long chatId,Integer messageId,String data) {
      if(data.equals(NBU_DATA.getData())) {
          if (settings.isCheckNBU()) {
              buttonBankNbu.setText(NBU.getButtonName());
              settings.setCheckNBU(false);
          } else {
              buttonBankNbu.setText(CHECK + NBU.getButtonName());
              settings.setCheckNBU(true);
          }
      }
      if(data.equals(PRIVAT_BANK_DATA.getData())) {
          if (settings.isCheckPrivatBank()) {
              buttonBankPrivat.setText(PRIVAT_BANK.getButtonName());
              settings.setCheckPrivatBank(false);
          } else {
              buttonBankPrivat.setText(CHECK + PRIVAT_BANK.getButtonName());
              settings.setCheckPrivatBank(true);
          }
      }
      if(data.equals(MONO_BANK_DATA.getData())) {
          if (settings.isCheckMonoBank()) {
              buttonBankMono.setText(MONO_BANK.getButtonName());
              settings.setCheckMonoBank(false);
          } else {
              buttonBankMono.setText(CHECK + MONO_BANK.getButtonName());
              settings.setCheckMonoBank(true);
          }
      }
        markupInline.setKeyboard(rowsInlineBank);
        EditMessageText edit = new EditMessageText();
        edit.setChatId(chatId.toString());
        edit.setMessageId(messageId);
        edit.setText("\uD83C\uDFE6  Выберите банк:");
        edit.setReplyMarkup(markupInline);
        return edit;
    }
}