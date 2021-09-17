package org.groupOne.Services;

public class Settings {
    private boolean isCheckUSD;
    private boolean isCheckEUR;
    private boolean isCheckRUB;
    private boolean isCheckMonoBank;
    private boolean isCheckPrivatBank;
    private boolean isCheckNBU;
    private Integer timeUpdate;
    private boolean isCheckDisableTimeUpdate;
    private Integer precision;
    private Long chatId;

    public Settings(Long chatId) {
        this.chatId = chatId;
        isCheckUSD = true;
        isCheckEUR = false;
        isCheckRUB = false;
        isCheckMonoBank = false;
        isCheckPrivatBank = true;
        isCheckNBU = false;
//        timeUpdate = 9;
//        isCheckDisableTimeUpdate = true;
        precision = 2;
    }

    public Long getChatId() {
        return chatId;
    }

    public boolean isCheckUSD() {
        return isCheckUSD;
    }

    public void setCheckUSD(boolean checkUSD) {
        isCheckUSD = checkUSD;
    }

    public boolean isCheckEUR() {
        return isCheckEUR;
    }

    public void setCheckEUR(boolean checkEUR) {
        isCheckEUR = checkEUR;
    }

    public boolean isCheckRUB() {
        return isCheckRUB;
    }

    public void setCheckRUB(boolean checkRUB) {
        isCheckRUB = checkRUB;
    }

    public boolean isCheckMonoBank() {
        return isCheckMonoBank;
    }

    public void setCheckMonoBank(boolean checkMonoBank) {
        isCheckMonoBank = checkMonoBank;
    }

    public boolean isCheckPrivatBank() {
        return isCheckPrivatBank;
    }

    public void setCheckPrivatBank(boolean checkPrivatBank) {
        isCheckPrivatBank = checkPrivatBank;
    }

    public boolean isCheckNBU() {
        return isCheckNBU;
    }

    public void setCheckNBU(boolean checkNBU) {
        isCheckNBU = checkNBU;
    }

    public Integer getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Integer timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public boolean isCheckDisableTimeUpdate() {
        return isCheckDisableTimeUpdate;
    }

    public void setCheckDisableTimeUpdate(boolean checkDisableTimeUpdate) {
        isCheckDisableTimeUpdate = checkDisableTimeUpdate;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }
}