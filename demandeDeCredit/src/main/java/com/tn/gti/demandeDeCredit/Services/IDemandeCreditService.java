package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import com.tn.gti.demandeDeCredit.Entities.Enum.TypeU;

import java.util.List;

public interface IDemandeCreditService {
    DemandeCredit addDemandeCredit(DemandeCredit demandeCredit);

    DemandeCredit updateDemandeCredit(DemandeCredit demandeCredit);

    Boolean deleteDemandeCredit(Long idD);

    List<DemandeCredit> getAllDemandeCredit();

    DemandeCredit getDemandeCreditById(Long idD);

    List<DemandeCredit> findAllDemandeCreditsByUser(Long idU);

    Float simulateur(Float montant, Integer duree, Float interet, TypeU unite);
    DemandeCredit createDemandeCredit(Long idU, DemandeCredit demandeCredit);

}
