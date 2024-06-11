package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import com.tn.gti.demandeDeCredit.Repositories.IDemandeCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/demandeCredit")
@RequiredArgsConstructor
public class DemandeCreditService implements IDemandeCreditService{

    @Autowired
    private final IDemandeCreditRepository iDemandeCreditRepository;
    @Override
    public DemandeCredit addDemandeCredit(DemandeCredit demandeCredit) {
        return iDemandeCreditRepository.save(demandeCredit);    }

    @Override
    public DemandeCredit updateDemandeCredit(DemandeCredit demandeCredit) {

        return iDemandeCreditRepository.save(demandeCredit);
    }

    @Override
    public Boolean deleteDemandeCredit(Long idD) {
        Optional<DemandeCredit> demandeCreditOptional = iDemandeCreditRepository.findById(idD);
        if (demandeCreditOptional.isPresent()) {
            iDemandeCreditRepository.deleteById(idD);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
    }

    @Override
    public List<DemandeCredit> getAllDemandeCredit() {

        return (List<DemandeCredit>) iDemandeCreditRepository.findAll();

    }

    @Override
    public DemandeCredit getDemandeCreditById(Long idD) {
        return iDemandeCreditRepository.findById(idD).orElse(null);

    }

    public List<DemandeCredit> findAllDemandeCreditsByUser(Long idU) {
        return iDemandeCreditRepository.findAllByIdU(idU);
    }
}
