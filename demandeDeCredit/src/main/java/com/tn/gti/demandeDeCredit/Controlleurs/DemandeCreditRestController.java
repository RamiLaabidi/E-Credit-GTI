package com.tn.gti.demandeDeCredit.Controlleurs;

import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import com.tn.gti.demandeDeCredit.Services.IDemandeCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/demandeCredit")
@RestController
@RequiredArgsConstructor
public class DemandeCreditRestController {
    private final IDemandeCreditService iDemandeCreditService;

    @PostMapping("/add")
    public DemandeCredit addDemandeCredit(@RequestBody DemandeCredit demandeCredit) {

        return iDemandeCreditService.addDemandeCredit(demandeCredit);

    }

    @PutMapping("/update")
    public DemandeCredit updateDemandeCredit(@RequestBody DemandeCredit demandeCredit) {

        return iDemandeCreditService.updateDemandeCredit(demandeCredit);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDemandeCredit(@PathVariable("idD") Long idD) {
        boolean deleted = iDemandeCreditService.deleteDemandeCredit(idD);
        if (deleted) {
            return ResponseEntity.ok("DemandeCredit deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DemandeCredit with ID " + idD + " does not exist.");
        }
    }

    @GetMapping("/get/{idD}")
    public DemandeCredit getDemandeCreditById(@PathVariable("idD") Long idD) {
        return iDemandeCreditService.getDemandeCreditById(idD);
    }


    @GetMapping("/getAllDemandeCredit")
    public List<DemandeCredit> getAllDemandeCredit() {
        return ResponseEntity.ok().body(iDemandeCreditService.getAllDemandeCredit()).getBody();

    }


    @GetMapping("/user/{user-idU}")
    public ResponseEntity<List<DemandeCredit>> findAllDemandeCredits(
            @PathVariable("user-idU") Long idU
    ) {
        return ResponseEntity.ok(iDemandeCreditService.findAllDemandeCreditsByUser(idU));
    }


}





