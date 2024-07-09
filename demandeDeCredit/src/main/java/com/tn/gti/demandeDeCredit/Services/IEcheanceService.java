package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.Echeance;
import com.tn.gti.demandeDeCredit.Entities.Enum.TypeU;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IEcheanceService {
    Echeance saveEcheance(Echeance echeance);
    Echeance updateEcheance(Echeance echeance);
    void deleteEcheance(Long id);
    Echeance getEcheanceById(Long id);
    List<Echeance> getAllEcheances();
   // public Set<Echeance> genererEcheances(float montant, int duree, float interet, TypeU unite);
    //public Set<Echeance> genererEcheances(float montant, int duree, float interet, TypeU unite, Long contratId);
}
