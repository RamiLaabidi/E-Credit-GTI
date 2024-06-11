package com.tn.gti.demandeDeCredit.Controlleurs;

import com.tn.gti.demandeDeCredit.Entities.PieceJointe;
import com.tn.gti.demandeDeCredit.Services.IPieceJointeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pieceJointe")
@RestController
@RequiredArgsConstructor
public class PieceJointeRestController {
    private final IPieceJointeService iPieceJointeService;

    @PostMapping("/add")
    public PieceJointe addPieceJointe(@RequestBody PieceJointe pieceJointe) {

        return iPieceJointeService.addPieceJointe(pieceJointe);

    }

    @PutMapping("/update")
    public PieceJointe updatePieceJointe(@RequestBody PieceJointe pieceJointe) {

        return iPieceJointeService.updatePieceJointe(pieceJointe);
    }


    @DeleteMapping("/delete/{idP}")
    public ResponseEntity<String> deletePieceJointe(@PathVariable("idP") Long idP) {
        boolean deleted = iPieceJointeService.deletePieceJointe(idP);
        if (deleted) {
            return ResponseEntity.ok("PieceJointe deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PieceJointe with ID " + idP + " does not exist.");
        }
    }

    @GetMapping("/get/{idP}")
    public PieceJointe getPieceJointeById(@PathVariable("idP") Long idP) {
        return iPieceJointeService.getPieceJointeById(idP);
    }


    @GetMapping("/getAllPieceJointe")
    public List<PieceJointe> getAllPieceJointe() {
        return ResponseEntity.ok().body(iPieceJointeService.getAllPieceJointe()).getBody();

    }

}
