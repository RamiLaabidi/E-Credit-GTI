package com.tn.gti.demandeDeCredit.Controlleurs;


import com.tn.gti.demandeDeCredit.Entities.Echeance;
import com.tn.gti.demandeDeCredit.Entities.Enum.TypeU;
import com.tn.gti.demandeDeCredit.Services.IEcheanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/echeance")
public class EcheanceController {

    @Autowired
    private IEcheanceService echeanceService;

    @PostMapping
    public Echeance createEcheance(@RequestBody Echeance echeance) {
        return echeanceService.saveEcheance(echeance);
    }

    @PutMapping("/{id}")
    public Echeance updateEcheance(@PathVariable Long id, @RequestBody Echeance echeance) {
        echeance.setIdE(id);
        return echeanceService.updateEcheance(echeance);
    }

    @DeleteMapping("/{id}")
    public void deleteEcheance(@PathVariable Long id) {
        echeanceService.deleteEcheance(id);
    }

    @GetMapping("/{id}")
    public Echeance getEcheanceById(@PathVariable Long id) {
        return echeanceService.getEcheanceById(id);
    }

    @GetMapping
    public List<Echeance> getAllEcheances() {
        return echeanceService.getAllEcheances();
    }




    /*@PostMapping("/echeance")
    public ResponseEntity<Set<Echeance>> genererEcheances(
            @RequestParam float montant,
            @RequestParam int duree,
            @RequestParam float interet,
            @RequestParam TypeU unite)

    {

        Set<Echeance> echeances = echeanceService.genererEcheances(montant, duree, interet, unite);
        return new ResponseEntity<>(echeances, HttpStatus.CREATED);
    }*/
}

