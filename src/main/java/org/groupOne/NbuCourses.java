package org.groupOne;

import java.math.BigDecimal;

class NbuCourses {

    static private final String BANK_NAME = "НБУ";

    public static String getBankName() {
        return BANK_NAME;
    }

    private String cc;
    private BigDecimal rate;

    public String getCc() {
        return cc;
    }

    public void setCc(String сс) {
        this.cc = сс;
    }


    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "NbuCourses{" +
                "cc='" + cc + '\'' +
                ", rate=" + rate +
                '}';
    }
}
