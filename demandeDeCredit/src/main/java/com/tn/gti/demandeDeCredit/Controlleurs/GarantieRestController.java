package com.tn.gti.demandeDeCredit.Controlleurs;

import com.tn.gti.demandeDeCredit.Entities.Garantie;
import com.tn.gti.demandeDeCredit.Services.IGarantieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/garantie")
@RestController
@RequiredArgsConstructor
public class GarantieRestController {
    private final IGarantieService iGarantieService;

    @PostMapping("/add")
    public Garantie addGarantie(@RequestBody Garantie garantie) {

        return iGarantieService.addGarantie(garantie);

    }

    @PutMapping("/update")
    public Garantie updateGarantie(@RequestBody Garantie garantie) {

        return iGarantieService.updateGarantie(garantie);
    }


    @DeleteMapping("/delete/{idG}")
    public ResponseEntity<String> deleteGarantie(@PathVariable("idG") Long idG) {
        boolean deleted = iGarantieService.deleteGarantie(idG);
        if (deleted) {
            return ResponseEntity.ok("Garantie deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garantie with ID " + idG + " does not exist.");
        }
    }

    @GetMapping("/get/{idG}")
    public Garantie getGarantieById(@PathVariable("idG") Long idG) {
        return iGarantieService.getGarantieById(idG);
    }


    @GetMapping("/getAllGarantie")
    public List<Garantie> getAllGarantie() {
        return ResponseEntity.ok().body(iGarantieService.getAllGarantie()).getBody();

    }

}
