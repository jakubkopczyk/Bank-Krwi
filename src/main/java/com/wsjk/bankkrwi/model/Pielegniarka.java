package com.wsjk.bankkrwi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="pielegniarka")
public class Pielegniarka {

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

    @OneToMany(mappedBy = "pielegniarka")
    private List<PobranieKrwi> pobraniaKrwi;

    public List<PobranieKrwi> getBloodCollections() {
        return pobraniaKrwi;
    }

    public void setBloodCollections(List<PobranieKrwi> pobraniaKrwi) {
        this.pobraniaKrwi = pobraniaKrwi;
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

    public void setImie(String name) {
        this.imie = name;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String surname) {
        this.nazwisko = surname;
    }

}