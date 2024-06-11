package com.tn.gti.demandeDeCredit.Controlleurs;

import com.tn.gti.demandeDeCredit.Entities.DossierCredit;
import com.tn.gti.demandeDeCredit.Entities.DossierCredit;
import com.tn.gti.demandeDeCredit.Services.IDossierCreditService;
import com.tn.gti.demandeDeCredit.Services.IDossierCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dossierCredit")
@RestController
@RequiredArgsConstructor
public class DossierCreditRestController {
    private final IDossierCreditService iDossierCreditService;

    @PostMapping("/add")
    public DossierCredit addDossierCredit(@RequestBody DossierCredit dossierCredit) {

        return iDossierCreditService.addDossierCredit(dossierCredit);

    }

    @PutMapping("/update")
    public DossierCredit updateDossierCredit(@RequestBody DossierCredit dossierCredit) {

        return iDossierCreditService.updateDossierCredit(dossierCredit);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDossierCredit(@PathVariable("idD") Long idDoss) {
        boolean deleted = iDossierCreditService.deleteDossierCredit(idDoss);
        if (deleted) {
            return ResponseEntity.ok("DossierCredit deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DossierCredit with ID " + idDoss + " does not exist.");
        }
    }

    @GetMapping("/get/{idDoss}")
    public DossierCredit getDossierCreditById(@PathVariable("idDoss") Long idDoss) {
        return iDossierCreditService.getDossierCreditById(idDoss);
    }


    @GetMapping("/getAllDossierCredit")
    public List<DossierCredit> getAllDossierCredit() {
        return ResponseEntity.ok().body(iDossierCreditService.getAllDossierCredit()).getBody();

    }

}
