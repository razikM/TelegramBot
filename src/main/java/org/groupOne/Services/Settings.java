package org.groupOne.Services;

public class Settings {

    private boolean isCheckUSD;
    private boolean isCheckEUR;
    private boolean isCheckRUR;
    private boolean isCheckMonoBank;
    private boolean isCheckPrivatBank;
    private boolean isCheckNBU;
    private Integer TimeUpdate;
    private boolean isCheckDisableTimeUpdate;
    private Integer precision;
    private Long chatId;
    private Float buyUSD;
    private Float buyEUR;
    private Float buyRUR;
    private  Float saleUSD;
    private  Float saleEUR;
    private  Float saleRUR;


    public Settings(Long chatId) {
        this.chatId = chatId;
        isCheckUSD = true;
        isCheckEUR = true;
        isCheckRUR = true;
        isCheckMonoBank = false;
        isCheckPrivatBank = false;
        isCheckNBU = true;
        TimeUpdate = 9;
        isCheckDisableTimeUpdate = true;
        precision = 4;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Float getBuyUSD() {
        return buyUSD;
    }

    public void setBuyUSD(Float buyUSD) {
        this.buyUSD = buyUSD;
    }

    public Float getBuyEUR() {
        return buyEUR;
    }

    public void setBuyEUR(Float buyEUR) {
        this.buyEUR = buyEUR;
    }

    public Float getBuyRUR() {
        return buyRUR;
    }

    public void setBuyRUR(Float buyRUR) {
        this.buyRUR = buyRUR;
    }

    public Float getSaleUSD() {
        return saleUSD;
    }

    public void setSaleUSD(Float saleUSD) {
        this.saleUSD = saleUSD;
    }

    public Float getSaleEUR() {
        return saleEUR;
    }

    public void setSaleEUR(Float saleEUR) {
        this.saleEUR = saleEUR;
    }

    public Float getSaleRUR() {
        return saleRUR;
    }

    public void setSaleRUR(Float saleRUR) {
        this.saleRUR = saleRUR;
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

    public boolean isCheckRUR() {
        return isCheckRUR;
    }

    public void setCheckRUR(boolean checkRUR) {
        isCheckRUR = checkRUR;
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
        return TimeUpdate;
    }

    public void setTimeUpdate(Integer timeUpdate) {
        TimeUpdate = timeUpdate;
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
