package org.groupOne.Services.button_enam;

public enum ButtonData {

  INFO_DATA("get_info"),
  SETTINGS_DATA("get_settings"),

  PRECISION_DATA("callback_precision"),
  PRECISION_TWO_DATA("callback_precision_two"),
  PRECISION_THREE_DATA("callback_precision_three"),
  PRECISION_FOUR_DATA("callback_precision_four"),

  //PRECISION_TWO_DATA_CHECKED("callback_precision_two_checked"),
  //PRECISION_THREE_DATA_CHECKED("callback_precision_three_checked"),
  //PRECISION_FOUR_DATA_CHECKED("callback_precision_four_checked"),

  BANK_DATA("callback_bank"),
  MONO_BANK_DATA("callback_mono_bank"),
  PRIVAT_BANK_DATA("callback_privat_bank"),
  NBU_DATA("callback_nbu_bank"),

//  MONO_BANK_DATA_CHECKED("callback_mono_bank_checked"),
//  PRIVAT_BANK_DATA_CHECKED("callback_privat_bank_checked"),
//  NBU_DATA_CHECKED("callback_nbu_bank_checked"),

  CURRENCY_DATA("callback_currency"),

  USD_DATA("callback_USD"),
  EUR_DATA("callback_EUR"),
  RUB_DATA("callback_RUB"),

//  USD_DATA_CHECKED("callback_USD_checked"),
//  EUR_DATA_CHECKED("callback_EUR_checked"),
//  RUB_DATA_CHECKED("callback_RUB_checked"),

  TIME_UPDATE_DATA("callback_time_update");

  private final String data;


  ButtonData(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }
}