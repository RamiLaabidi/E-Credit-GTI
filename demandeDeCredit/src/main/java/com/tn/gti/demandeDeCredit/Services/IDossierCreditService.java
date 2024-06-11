package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import com.tn.gti.demandeDeCredit.Entities.DossierCredit;

import java.util.List;

public interface IDossierCreditService {
    DossierCredit addDossierCredit(DossierCredit dossierCredit);

    DossierCredit updateDossierCredit(DossierCredit dossierCredit);

    Boolean deleteDossierCredit(Long idDoss);

    List<DossierCredit> getAllDossierCredit();

    DossierCredit getDossierCreditById(Long idD);
}
