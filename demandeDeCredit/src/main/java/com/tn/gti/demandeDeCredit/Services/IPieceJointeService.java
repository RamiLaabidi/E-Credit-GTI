package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.PieceJointe;

import java.util.List;

public interface IPieceJointeService {
    PieceJointe addPieceJointe(PieceJointe pieceJointe);

    PieceJointe updatePieceJointe(PieceJointe pieceJointe);

    Boolean deletePieceJointe(Long idDoss);

    List<PieceJointe> getAllPieceJointe();

    PieceJointe getPieceJointeById(Long idD);

}
