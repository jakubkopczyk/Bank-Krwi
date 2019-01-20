package com.wsjk.bankkrwi.repository;

import com.wsjk.bankkrwi.model.Pielegniarka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PielegniarkaRepository extends JpaRepository<Pielegniarka, Long> {

    @Query(value = "Select * from pielegniarka as n order by lower(n.nazwisko) asc, lower(n.imie) asc", nativeQuery = true)
    List<Pielegniarka> findAllByOrderByNazwiskoAndImieAsc();

    @Query(value  =  "select * from  pielegniarka as n where  upper(n.nazwisko)  like  :letter% order by lower(n.nazwisko) asc, lower(n.imie) asc",  nativeQuery  =  true)
    List<Pielegniarka> findAllByNazwiskoStartingWith(@Param("letter") String letter);

}
