package org.groupOne;

public class PrivatRate extends PrivatApi{

    public final String bankName = "PrivatBank";
    private double buy;
    private String ccy;
    private double sale;

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(Long buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(Long sale) {
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
