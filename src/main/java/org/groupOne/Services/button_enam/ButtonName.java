package org.groupOne.Services.button_enam;

public enum ButtonName {
    START("/start"),
    HELP("/help"),
    STOP("/stop"),

    INFO("❗ Получить инфо"),
    SETTINGS("⚙️ Настройки"),

    BANK("\uD83C\uDFE6 Банк"),
    MONOBANK("МоноБанк"),
    PRIVATBANK("ПриватБанк"),
    NBU("НБУ"),

    CURRENCY("\u200B\uD83D\uDCB4\u200B\uD83D\uDCB1\u200B\uD83D\uDCB5\u200B Валюта"),
    USD("USD"),
    EUR("EUR"),
    RUR("RUR"),

    PRECISION("\uD83C\uDFAF Кол-во знаков после запятой"),
    PRECISION_TWO("2"),
    PRECISION_THREE("3"),
    PRECISION_FOUR("4"),

    TIME_UPDATE("\uD83D\uDD57 Время оповещений"),
    TIME_UPDATE_NINE("9"),
    TIME_UPDATE_THEN("10"),
    TIME_UPDATE_ELEVEN("11"),
    TIME_UPDATE_TWELVE("12"),
    TIME_UPDATE_THIRTEEN("13"),
    TIME_UPDATE_FOURTEEN("14"),
    TIME_UPDATE_FIFTEEN("15"),
    TIME_UPDATE_SIXTEEN("16"),
    TIME_UPDATE_SEVENTEEN("17"),
    TIME_UPDATE_EIGHTEEN("18"),
    TIME_UPDATE_DISABLE("Выключить уведомления");
    private String buttonName;

    ButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
