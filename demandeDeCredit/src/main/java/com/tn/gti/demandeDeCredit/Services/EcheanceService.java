package com.tn.gti.demandeDeCredit.Services;


import com.tn.gti.demandeDeCredit.Entities.Echeance;
import com.tn.gti.demandeDeCredit.Repositories.IContratCreditRepository;
import com.tn.gti.demandeDeCredit.Repositories.IEcheanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequestMapping("/echeances")
@RequiredArgsConstructor
public class EcheanceService implements IEcheanceService {

    @Autowired
    private IEcheanceRepository iEcheanceRepoistory;
    private IContratCreditRepository iContratCreditRepository;

    @Override
    public Echeance saveEcheance(Echeance echeance) {
        return iEcheanceRepoistory.save(echeance);
    }

    @Override
    public Echeance updateEcheance(Echeance echeance) {
        return iEcheanceRepoistory.save(echeance);
    }

    @Override
    public void deleteEcheance(Long id) {
        iEcheanceRepoistory.deleteById(id);
    }

    @Override
    public Echeance getEcheanceById(Long id) {
        return iEcheanceRepoistory.findById(id).orElse(null);
    }

    @Override
    public List<Echeance> getAllEcheances() {
        return iEcheanceRepoistory.findAll();
    }

    /*public Set<Echeance> genererEcheances(float montant, int duree, float interet, TypeU unite, Long contratId) {
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

        // Calcul de la mensualité en utilisant la formule de l'annuité
        float mensualite = (montant * interet) / (1 - (float) Math.pow(1 + interet, -duree));

        Calendar calendar = Calendar.getInstance();

        // Création des échéances
        Set<Echeance> nouvellesEcheances = new HashSet<>();
        float capitalRestant = montant;

        // Récupérer le contrat correspondant
        ContratCredit contratCredit = iContratCreditRepository.findById(contratId)
                .orElseThrow(() -> new IllegalArgumentException("ContratCredit not found"));

        for (int i = 0; i < duree; i++) {
            Echeance echeance = new Echeance();
            // Définir le dernier jour du mois courant
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            echeance.setDatePaiement(calendar.getTime()); // Date de paiement de l'échéance

            float interetsPayes = capitalRestant * interet; // Intérêts payés pour cette échéance
            float capitalRembourse = mensualite - interetsPayes; // Capital remboursé pour cette échéance

            // Mise à jour du capital restant dû
            capitalRestant -= capitalRembourse;

            echeance.setCapitalRembourse(capitalRembourse); // Capital remboursé
            echeance.setCapitalRestantDu(capitalRestant); // Capital restant dû
            echeance.setInteretsPayes(interetsPayes); // Intérêts payés
            echeance.setMensualite(mensualite); // Mensualité
            echeance.setContratCredit(contratCredit); // Associer l'échéance au contrat

            // Enregistrer l'échéance en base de données
            echeance = iEcheanceRepoistory.save(echeance);

            nouvellesEcheances.add(echeance);

            // Passage à la même date du mois suivant
            calendar.add(Calendar.MONTH, 1);
        }
        // Ajouter les nouvelles échéances au contrat
        contratCredit.getEcheances().addAll(nouvellesEcheances);
        iContratCreditRepository.save(contratCredit);

        return nouvellesEcheances;
    }*/
}

