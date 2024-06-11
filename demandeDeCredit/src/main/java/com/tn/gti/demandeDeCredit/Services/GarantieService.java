package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.DossierCredit;
import com.tn.gti.demandeDeCredit.Entities.Garantie;
import com.tn.gti.demandeDeCredit.Repositories.IDossierCreditRepository;
import com.tn.gti.demandeDeCredit.Repositories.IGarantieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/garantie")
@RequiredArgsConstructor
public class GarantieService implements IGarantieService{

    @Autowired
    private final IGarantieRepository iGarantieRepository;
    @Override
    public Garantie addGarantie(Garantie garantie) {
        return iGarantieRepository.save(garantie);     }

    @Override
    public Garantie updateGarantie(Garantie garantie) {

        return iGarantieRepository.save(garantie);
    }

    @Override
    public Boolean deleteGarantie(Long idG) {
        Optional<Garantie> garantieOptional = iGarantieRepository.findById(idG);
        if (garantieOptional.isPresent()) {
            iGarantieRepository.deleteById(idG);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
    }

    @Override
    public List<Garantie> getAllGarantie() {
        return (List<Garantie>) iGarantieRepository.findAll();
    }

    @Override
    public Garantie getGarantieById(Long idG) {
        return iGarantieRepository.findById(idG).orElse(null);
    }
}
