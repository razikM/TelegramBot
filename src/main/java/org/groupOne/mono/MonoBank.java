package org.groupOne.mono;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class MonoBank {

    private String bankName = "Mono";
    @SerializedName("currencyCodeA")
    private String currency;
    @SerializedName("rateBuy")
    private BigDecimal buyRate;
    @SerializedName("rateSell")
    private BigDecimal sellRate;

    public MonoBank() {}

    public MonoBank(String bankName, String currency, BigDecimal buyRate, BigDecimal sellRate) {
        this.bankName = bankName;
        this.currency = currency;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    @Override
    public String toString() {
        return "BankResponse{" +
                "bankName='" + bankName + '\'' +
                ", currency='" + currency + '\'' +
                ", buyRate=" + buyRate +
                ", sellRate=" + sellRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonoBank that = (MonoBank) o;
        return bankName.equals(that.bankName) && currency.equals(that.currency) && buyRate.equals(that.buyRate) && sellRate.equals(that.sellRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, currency, buyRate, sellRate);
    }
}
