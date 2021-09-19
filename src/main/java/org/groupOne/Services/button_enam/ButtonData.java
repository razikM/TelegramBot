package org.groupOne.Services.button_enam;

public enum ButtonData {

  INFO_DATA("get_info"),
  SETTINGS_DATA("get_settings"),

  PRECISION_DATA("callback_precision"),
  PRECISION_TWO_DATA("callback_precision_two"),
  PRECISION_THREE_DATA("callback_precision_three"),
  PRECISION_FOUR_DATA("callback_precision_four"),

  BANK_DATA("callback_bank"),
  MONO_BANK_DATA("callback_mono_bank"),
  PRIVAT_BANK_DATA("callback_privat_bank"),
  NBU_DATA("callback_nbu_bank"),

  CURRENCY_DATA("callback_currency"),

  USD_DATA("callback_USD"),
  EUR_DATA("callback_EUR"),
  RUB_DATA("callback_RUB"),

  TIME_UPDATE_DATA("callback_time_update");

  private final String data;

  ButtonData(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }
}