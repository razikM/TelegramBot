package org.groupOne.privat_api;

import org.groupOne.privat_api.PrivatApi;

import java.math.BigDecimal;

public class PrivatRate extends PrivatApi {



    public final String bankName = "Privat";
    private BigDecimal buy;
    private String ccy;
    private BigDecimal sale;

    public String getBankName() {
        return bankName;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return bankName  +
                " Currency is " + ccy +
                ", buyrate is = " + buy +
                ", salerate is = " + sale;
    }

}
