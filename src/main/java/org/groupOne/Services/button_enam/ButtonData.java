package org.groupOne.Services.button_enam;

public enum ButtonData {


  INFO_DATA("get_info"),
  SETTINGS_DATA("get_settings"),
  PRECISION_DATA("callback_precision"),
  BANK_DATA("callback_bank"),
  CURRENCY_DATA("callback_currency"),
  TIME_UPDATE_DATA("callback_time_update");
  private String data;


  ButtonData(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }
}