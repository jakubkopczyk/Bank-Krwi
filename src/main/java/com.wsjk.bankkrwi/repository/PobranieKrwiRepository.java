package com.wsjk.bankkrwi.repository;

import com.wsjk.bankkrwi.model.PobranieKrwi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PobranieKrwiRepository extends JpaRepository<PobranieKrwi, Long> {

    List<PobranieKrwi> findAllByOrderByDateDesc();

    @Query(value  =  "SELECT bg.nazwa, sum(b.ilosc) FROM pobraniekrwi as b \n" +
            "join dawca as p  on p.id = b.dawca_id \n" +
            "join grupakrwi as bg on p.grupa_krwi_id=bg.id\n" +
            "group by p.grupa_krwi_id",  nativeQuery  =  true)
      List <Object> findSumByGrupaKrwi();

    List<PobranieKrwi> findAllByDawca_Id(Long id);

}
