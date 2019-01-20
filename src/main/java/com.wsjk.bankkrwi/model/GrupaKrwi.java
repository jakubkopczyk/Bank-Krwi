package com.wsjk.bankkrwi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="grupakrwi")
public class GrupaKrwi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazwa;

    @OneToMany(mappedBy = "grupaKrwi")
    private List<Dawca> dawcy;

    public List<Dawca> getDawcy() {
        return dawcy;
    }

    public void setDawcy(List<Dawca> dawcy) {
        this.dawcy = dawcy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String name) {
        this.nazwa = name;
    }

}