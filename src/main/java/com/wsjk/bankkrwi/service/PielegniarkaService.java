package com.wsjk.bankkrwi.service;

import com.wsjk.bankkrwi.model.Pielegniarka;
import com.wsjk.bankkrwi.repository.PielegniarkaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PielegniarkaService {

    private PielegniarkaRepository pielegniarkaRepository;

    public PielegniarkaService(PielegniarkaRepository pielegniarkaRepository) {
        this.pielegniarkaRepository = pielegniarkaRepository;
    }

    @Transactional
    public void update(Pielegniarka pielegniarka){
        Optional<Pielegniarka> byId = pielegniarkaRepository.findById(pielegniarka.getId());
        Pielegniarka pielegniarkaUpdate = byId.get();
        pielegniarkaUpdate.setImie(pielegniarka.getImie());
        pielegniarkaUpdate.setNazwisko(pielegniarka.getNazwisko());
    }

}