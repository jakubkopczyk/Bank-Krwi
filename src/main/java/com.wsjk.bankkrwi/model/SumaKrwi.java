package com.wsjk.bankkrwi.model;

import java.math.BigDecimal;

public class SumaKrwi {

    private BigDecimal ilosc;

    private String nazwa;

    public SumaKrwi(String nazwa, BigDecimal ilosc) {
        this.ilosc = ilosc;
        this.nazwa = nazwa;
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
