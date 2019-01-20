package com.wsjk.bankkrwi.repository;

import com.wsjk.bankkrwi.model.Dawca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DawcaRepository extends JpaRepository<Dawca, Long> {

    Optional<Dawca> findByNazwiskoAndImieIgnoreCase(String surname, String name);

    @Query(value = "Select * from dawca as p order by lower(p.nazwisko) asc, lower(p.imie) asc", nativeQuery = true)
    List<Dawca> findAllByOrderByNazwiskoAndImieAsc();

    @Query(value  =  "select * from  dawca as p where  upper(p.nazwisko)  like  :letter% order by lower(p.nazwisko) asc, lower(p.imie) asc",  nativeQuery  =  true)
    List<Dawca> findAllByNazwiskoStartingWith(@Param("letter") String letter);

    List<Dawca> findAllByGrupaKrwi_IdOrderByNazwisko(Long id);
}
