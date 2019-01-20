package com.wsjk.bankkrwi.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@Entity
@Table(name="pobraniekrwi")
public class PobranieKrwi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
    private String date;

    @NotNull
    @Min(1) //ml
    @Max(500)   //ml
    private BigDecimal ilosc;

    @ManyToOne()
    private Dawca dawca;

    @ManyToOne()
    private Pielegniarka pielegniarka;

    public PobranieKrwi() {
    }



    public PobranieKrwi(@NotNull @Min(1) @Max(500) BigDecimal ilosc, Dawca dawca) {
        this.ilosc = ilosc;
        this.dawca = dawca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public Dawca getDawca() {
        return dawca;
    }

    public void setDawca(Dawca dawca) {
        this.dawca = dawca;
    }

    public Pielegniarka getPielegniarka() {
        return pielegniarka;
    }

    public void setPielegniarka(Pielegniarka pielegniarka) {
        this.pielegniarka = pielegniarka;
    }
}
