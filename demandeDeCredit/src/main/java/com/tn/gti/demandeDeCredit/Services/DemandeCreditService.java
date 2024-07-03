package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import com.tn.gti.demandeDeCredit.Entities.Enum.TypeU;
import com.tn.gti.demandeDeCredit.Repositories.IDemandeCreditRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/demandeCredit")
@RequiredArgsConstructor
public class DemandeCreditService implements IDemandeCreditService {

    @Autowired
    private final IDemandeCreditRepository iDemandeCreditRepository;
    @Autowired

    private RestTemplate restTemplate;


    @Override
    public DemandeCredit addDemandeCredit(DemandeCredit demandeCredit) {
        return iDemandeCreditRepository.save(demandeCredit);
    }

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

    @Override
    public List<DemandeCredit> findAllDemandeCreditsByUser(Long idU) {
        return iDemandeCreditRepository.findAllByIdU(idU);
    }


    @Override
    public Float simulateur(Float montant, Integer duree, Float interet, TypeU unite) {
        // Calcul du taux d'intérêt annuel, trimestriel et semestriel à partir du taux mensuel
        float interetAnnuelle = (float) (Math.pow(1 + interet, 12) - 1);
        float interetTrimestriel = (float) (Math.pow(1 + interet, 3) - 1);
        float interetSemestrielle = (float) (Math.pow(1 + interet, 6) - 1);
        // Ajustement du taux d'intérêt et du nombre d'échéances en fonction de l'unité
        switch (unite) {
            case MENSUELLE:
                // Le taux d'intérêt est déjà mensuel, aucune conversion nécessaire
                break;
            case TRIMESTRIELLE:
                interet = interetTrimestriel;
                duree /= 3; // Convertir le nombre d'échéances en trimestrielles
                break;
            case SEMESTRIELLE:
                interet = interetSemestrielle;
                duree /= 6; // Convertir le nombre d'échéances en semestrielles
                break;
            case ANNUELLE:
                interet = interetAnnuelle;
                duree /= 12; // Convertir le nombre d'échéances en annuelles
                break;

        }
        // Calcul du montant total à payer en tenant compte de l'intérêt
        float montantTotal = montant * (1 + interet * duree);

        // Montant à payer par échéance
        return montantTotal / duree;
    }


    public DemandeCredit createDemandeCredit(Long idU, DemandeCredit demandeCredit) {
        // Appel au service d'authentification pour vérifier si l'utilisateur existe
        String userServiceUrl = "http://localhost:8077/api/v1/auth/authenticate/" + idU;
        User user = restTemplate.getForObject(userServiceUrl, User.class);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Assigner l'ID de l'utilisateur à la demande de crédit
        demandeCredit.setIdU(idU);

        // Sauvegarder la demande de crédit dans la base de données
        return iDemandeCreditRepository.save(demandeCredit);
    }
}
