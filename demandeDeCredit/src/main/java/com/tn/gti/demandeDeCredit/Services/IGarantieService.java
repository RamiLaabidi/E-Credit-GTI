package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.DossierCredit;
import com.tn.gti.demandeDeCredit.Entities.Garantie;

import java.util.List;

public interface IGarantieService {
    Garantie addGarantie(Garantie garantie);

    Garantie updateGarantie(Garantie garantie);

    Boolean deleteGarantie(Long idG);

    List<Garantie> getAllGarantie();

    Garantie getGarantieById(Long idG);
}
