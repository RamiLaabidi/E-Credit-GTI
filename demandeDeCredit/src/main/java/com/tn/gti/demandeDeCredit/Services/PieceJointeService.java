package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.PieceJointe;
import com.tn.gti.demandeDeCredit.Entities.PieceJointe;
import com.tn.gti.demandeDeCredit.Repositories.IPieceJointeRepository;
import com.tn.gti.demandeDeCredit.Repositories.IPieceJointeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Service
@RequestMapping("/pieceJointe")
@RequiredArgsConstructor
public class PieceJointeService implements IPieceJointeService{

    @Autowired
    private final IPieceJointeRepository iPieceJointeRepository;
    @Override
    public PieceJointe addPieceJointe(PieceJointe pieceJointe) {
        return iPieceJointeRepository.save(pieceJointe);     }

    @Override
    public PieceJointe updatePieceJointe(PieceJointe pieceJointe) {

        return iPieceJointeRepository.save(pieceJointe);
    }

    @Override
    public Boolean deletePieceJointe(Long idP) {
        Optional<PieceJointe> PieceJointeOptional = iPieceJointeRepository.findById(idP);
        if (PieceJointeOptional.isPresent()) {
            iPieceJointeRepository.deleteById(idP);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<PieceJointe> getAllPieceJointe() {
        return (List<PieceJointe>) iPieceJointeRepository.findAll();
    }

    @Override
    public PieceJointe getPieceJointeById(Long idP) {
        return iPieceJointeRepository.findById(idP).orElse(null);
    }
}