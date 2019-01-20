package com.wsjk.bankkrwi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="dawca")
public class Dawca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String imie;

    @NotNull
    @NotEmpty
    @NotBlank
    private String nazwisko;

    @ManyToOne()
    private GrupaKrwi grupaKrwi;

    @OneToMany(mappedBy = "dawca")
    private List<PobranieKrwi> pobraniaKrwi;

    public List<PobranieKrwi> getPobraniaKrwi() {
        return pobraniaKrwi;
    }

    public void setPobraniaKrwi(List<PobranieKrwi> pobraniaKrwi) {
        this.pobraniaKrwi = pobraniaKrwi;
    }

    public GrupaKrwi getGrupaKrwi() {
        return grupaKrwi;
    }

    public void setGrupaKrwi(GrupaKrwi grupaKrwi) {
        this.grupaKrwi = grupaKrwi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

}
