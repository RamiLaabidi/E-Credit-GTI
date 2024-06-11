package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.DossierCredit;
import com.tn.gti.demandeDeCredit.Entities.DossierCredit;
import com.tn.gti.demandeDeCredit.Repositories.IDossierCreditRepository;
import com.tn.gti.demandeDeCredit.Repositories.IDossierCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/dossierCredit")
@RequiredArgsConstructor
public class DossierCreditService implements IDossierCreditService{

    @Autowired
    private final IDossierCreditRepository iDossierCreditRepository;
    @Override
    public DossierCredit addDossierCredit(DossierCredit dossierCredit) {
        return iDossierCreditRepository.save(dossierCredit);    }

    @Override
    public DossierCredit updateDossierCredit(DossierCredit dossierCredit) {

        return iDossierCreditRepository.save(dossierCredit);
    }

    @Override
    public Boolean deleteDossierCredit(Long idDoss) {
        Optional<DossierCredit> dossierCreditOptional = iDossierCreditRepository.findById(idDoss);
        if (dossierCreditOptional.isPresent()) {
            iDossierCreditRepository.deleteById(idDoss);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
    }

    @Override
    public List<DossierCredit> getAllDossierCredit() {

        return (List<DossierCredit>) iDossierCreditRepository.findAll();

    }

    @Override
    public DossierCredit getDossierCreditById(Long idDoss) {
        return iDossierCreditRepository.findById(idDoss).orElse(null);

    }
}
