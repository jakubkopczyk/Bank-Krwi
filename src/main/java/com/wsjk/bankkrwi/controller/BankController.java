package com.wsjk.bankkrwi.controller;

import com.wsjk.bankkrwi.model.*;
import com.wsjk.bankkrwi.repository.DawcaRepository;
import com.wsjk.bankkrwi.repository.GrupaKrwiRepository;
import com.wsjk.bankkrwi.repository.PielegniarkaRepository;
import com.wsjk.bankkrwi.repository.PobranieKrwiRepository;
import com.wsjk.bankkrwi.service.PielegniarkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class BankController {

    private DawcaRepository dawcaRepository;

    private PielegniarkaRepository pielegniarkaRepository;
    private PielegniarkaService pielegniarkaService;

    private GrupaKrwiRepository grupaKrwiRepository;

    private PobranieKrwiRepository pobranieKrwiRepository;

    @Autowired
    public BankController(DawcaRepository dawcaRepository, GrupaKrwiRepository grupaKrwiRepository, PielegniarkaRepository pielegniarkaRepository, PielegniarkaService pielegniarkaService, PobranieKrwiRepository pobranieKrwiRepository) {
        this.dawcaRepository = dawcaRepository;
        this.pielegniarkaRepository = pielegniarkaRepository;
        this.pielegniarkaService = pielegniarkaService;
        this.grupaKrwiRepository = grupaKrwiRepository;
        this.pobranieKrwiRepository = pobranieKrwiRepository;
    }

    @GetMapping("/")
    public String homepage(Principal principal, Model model){
        model.addAttribute("user", principal.getName());
        return "index";
    }

    @GetMapping("/wszystkieGrupyKrwi")
    public String allBloodGroups(Model model) {
        List<GrupaKrwi> wszystkieGrupy = grupaKrwiRepository.findAll();
        model.addAttribute("bloodgroups", wszystkieGrupy);
        return "wszystkiegrupykrwi";
    }

    @GetMapping("/dodajDawce")
    public String addFormPatient(Model model) {
        List<GrupaKrwi> wszystkieGrupy = grupaKrwiRepository.findAll();
        model.addAttribute("bloodgroups", wszystkieGrupy);
        model.addAttribute("patient", new Dawca());
        return "dodajdawce";
    }

    @GetMapping("/wszyscyDawcy")
    public String allPatients(Model model) {
        List<Dawca> wszyscyDawcy = dawcaRepository.findAll();
        model.addAttribute("dawcy", wszyscyDawcy);
        char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        model.addAttribute("alphabet", alphabet);
        return "wszyscydawcy";
    }

    @GetMapping("/dawca_{nazwisko}_{imie}")
    public String getPatient(Model model, @PathVariable String nazwisko, @PathVariable String imie) {
        Optional<Dawca> dawcaByNazwisko = dawcaRepository.findByNazwiskoAndImieIgnoreCase(nazwisko, imie);
        dawcaByNazwisko.ifPresent(patient -> model.addAttribute("patients", patient));
        char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        model.addAttribute("alphabet", alphabet);
        return dawcaByNazwisko.map(patient -> "wszyscydawcy").orElse("brakdawcy");
    }

    @GetMapping("/pokazDawce")
    public String showPatient(@RequestParam String letter, Model model){
        List<Dawca> wszyscyDawcy = dawcaRepository.findAllByNazwiskoStartingWith(letter);
        model.addAttribute("dawcy", wszyscyDawcy);
        char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        model.addAttribute("alphabet", alphabet);
        if (wszyscyDawcy.size()>0)
            return "wszyscydawcy";
        else
            return "brakdawcy";
    }

    @GetMapping("/usunDawce")
    public String deletePatientSelect(Model model){
        List<Dawca> wszyscyDawcy = dawcaRepository.findAll();
        model.addAttribute("dawcy", wszyscyDawcy);
        return "usundawce";
    }

    @GetMapping("/usunD")
    public String usunDawce(@RequestParam Long id) {
        dawcaRepository.deleteById(id);
        return "redirect:/wszyscyDawcy";
    }

    @GetMapping("/dodajPielegniarke")
    public String addFormNurse(Model model) {
        model.addAttribute("nowaPielegniarka", new Pielegniarka());
        return "dodajpielegniarke";
    }

    @PostMapping("/zapiszPielegniarke")
    public String addNurse(@Valid Pielegniarka pielegniarka, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                String field = error.getField();
                System.out.println("Field: " + field + " invalid value: " + error.getRejectedValue());
            }
            model.addAttribute("form", pielegniarka);
            return "dodajpielegniarke";
        }
        pielegniarkaRepository.save(pielegniarka);
        return "redirect:/wszystkiePielegniarki";
    }

    @GetMapping("/wszystkiePielegniarki")
    public String wszystkiePielegniarki(Model model) {
        List<Pielegniarka> wszystkiePielegniarki = pielegniarkaRepository.findAll();
        model.addAttribute("pielegniarki", wszystkiePielegniarki);
        char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        model.addAttribute("alphabet", alphabet);
        return "wszystkiepielegniarki";
    }

    @GetMapping("/usunPielegniarke")
    public String deleteNurseSelect(Model model){
        List<Pielegniarka> wszystkiePielegniarki = pielegniarkaRepository.findAll();
        model.addAttribute("pielegniarki", wszystkiePielegniarki);
        return "usunpielegniarke";
    }

    @GetMapping("/usunP")
    public String usunP(@RequestParam Long id) {
        pielegniarkaRepository.deleteById(id);
        return "redirect:/wszystkiePielegniarki";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam Long id, Model model) {
        Optional<Pielegniarka> pielegniarkaById = pielegniarkaRepository.findById(id);
        pielegniarkaById.ifPresent(pielegniarka -> model.addAttribute("pielegniarki", pielegniarka));
        return pielegniarkaById.map(nurse -> "updatepielegniarki").orElse("brakpielegniarki");
    }

    @PostMapping("/updatePielegniarki")
    public String update(@Valid Pielegniarka pielegniarka, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                String field = error.getField();
                System.out.println("Field: " + field + " invalid value: " + error.getRejectedValue());
            }
            model.addAttribute("form", pielegniarka);
            return "updatepielegniarki";
        }
        pielegniarkaService.update(pielegniarka);
        List<Pielegniarka> wszystkiePielegniarki = pielegniarkaRepository.findAll();
        model.addAttribute("pielegniarki", wszystkiePielegniarki);
        char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        model.addAttribute("alphabet", alphabet);
        return "wszystkiepielegniarki";
    }

    @GetMapping("/pokazPielegniarke")
    public String showPielegniarke(@RequestParam String letter, Model model){
        List<Pielegniarka> wszystkiePielegniarki = pielegniarkaRepository.findAllByNazwiskoStartingWith(letter);
        model.addAttribute("pielegniarki", wszystkiePielegniarki);
        char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        model.addAttribute("alphabet", alphabet);
        if (wszystkiePielegniarki.size()>0)
            return "wszystkiepielegniarki";
        else
            return "brakpielegniarki";
    }

    @PostMapping("/zapiszDawce")
    public String addDawce(@Valid Dawca patient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                String field = error.getField();
                System.out.println("Field: " + field + " invalid value: " + error.getRejectedValue());
            }
            List<GrupaKrwi> wszystkieGrupyKrwi = grupaKrwiRepository.findAll();
            model.addAttribute("grupykrwi", wszystkieGrupyKrwi);
            model.addAttribute("form", patient);
            return "dodajdawce";
        }
        dawcaRepository.save(patient);
        return "redirect:/wszyscyDawcy";
    }

    @GetMapping("/dodajPobranieKrwi")
    public String addFormBloodcollection(Model model) {
        List<Dawca> wszyscyDawcy = dawcaRepository.findAllByOrderByNazwiskoAndImieAsc();
        model.addAttribute("dawcy", wszyscyDawcy);
        List<Pielegniarka> wszystkiePielegniarki = pielegniarkaRepository.findAllByOrderByNazwiskoAndImieAsc();
        model.addAttribute("pielegniarki", wszystkiePielegniarki);
        model.addAttribute("pobrania", new PobranieKrwi());
        model.addAttribute("errorField", null);
        return "dodajpobraniekrwi";
    }

    @PostMapping("/zapiszPobranieKrwi")
    public String addBloodcollection(@Valid PobranieKrwi bloodCollection, BindingResult bindingResult, Model model) {

        String errorField = "-";
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                String field = error.getField();
                System.out.println("Field: " + field + " invalid value: " + error.getRejectedValue());
                if(field.equals("amount"))
                    errorField += "ilość pobranej krwi";
                if(field.equals("date"))
                    errorField += "data";
                errorField += "-";
            }

            List<Dawca> wszyscyDawcy = dawcaRepository.findAllByOrderByNazwiskoAndImieAsc();
            model.addAttribute("dawcy", wszyscyDawcy);
            List<Pielegniarka> wszystkiePielegniarki = pielegniarkaRepository.findAllByOrderByNazwiskoAndImieAsc();
            model.addAttribute("pielegniarki", wszystkiePielegniarki);
            model.addAttribute("pobraniekrwi", bloodCollection);
            model.addAttribute("errorField", errorField);
            return "dodajpobraniekrwi";
        }
        pobranieKrwiRepository.save(bloodCollection);
        return "redirect:/wszystkiePobraniaKrwi";
    }

    @GetMapping("/wszystkiePobraniaKrwi")
    public String wszystkiePobraniaKrwi(Model model) {
        List<PobranieKrwi> wszystkiePobrania = pobranieKrwiRepository.findAllByOrderByDateDesc();
        model.addAttribute("pobraniakrwi", wszystkiePobrania);
        return "wszystkiepobraniakrwi";
    }

    @GetMapping("/usunPobranie")
    public String deleteBloodcollection(@RequestParam Long id){
        pobranieKrwiRepository.deleteById(id);
        return "redirect:/wszystkiePobraniaKrwi";
    }

    @GetMapping("/grupuj")
    public String chooseGroup(@RequestParam Long id, Model model){

        Optional<GrupaKrwi> groupById = grupaKrwiRepository.findById(id);
        groupById.ifPresent(bloodGroup -> model.addAttribute("grupakrwi", bloodGroup));

        List<Dawca> patients = dawcaRepository.findAllByGrupaKrwi_IdOrderByNazwisko(id);
        model.addAttribute("patients", patients);

        return "grupujdawcow";
    }

    @GetMapping("/daneDawcy")
    public String patientMore(@RequestParam Long id, Model model){

        Optional<Dawca> groupById = dawcaRepository.findById(id);
        groupById.ifPresent(dawca -> model.addAttribute("dawca", dawca));

        List<PobranieKrwi> patientCollection = pobranieKrwiRepository.findAllByDawca_Id(id);
        model.addAttribute("pobraniakrwi", patientCollection);

        return "danedawcy";

    }

    @GetMapping("/sumyGrupKrwi")
    public String groupBloodcollection(Model model) {

        List<Object> sumaKrwi = pobranieKrwiRepository.findSumByGrupaKrwi();

        Iterator iterator=sumaKrwi.iterator();
        List<SumaKrwi> sumaGrup = new ArrayList<>();
        while(iterator.hasNext()){
            Object [] obj = (Object[])iterator.next();
            sumaGrup.add(new SumaKrwi( (String) obj[0], (BigDecimal) obj[1]));
        }
        model.addAttribute("sumaKrwiGrup", sumaGrup);
        return "sumygrupkrwi";
    }

}