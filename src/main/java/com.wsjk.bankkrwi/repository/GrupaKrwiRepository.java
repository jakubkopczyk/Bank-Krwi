package com.wsjk.bankkrwi.repository;

import com.wsjk.bankkrwi.model.GrupaKrwi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrupaKrwiRepository extends JpaRepository<GrupaKrwi, Long> {

    List<GrupaKrwi> findAllByOrderByNazwa();
    Optional<GrupaKrwi> findById(Long id);
}
