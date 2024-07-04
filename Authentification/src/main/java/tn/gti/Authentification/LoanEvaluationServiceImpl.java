package tn.gti.Authentification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/evaluation")
@RequiredArgsConstructor
public class LoanEvaluationServiceImpl {

    public double calculateScore(User user, CompteBancaire compteBancaire, DemandeCredit demandeCredit) {
        // Implémentation du calcul du score basé sur les critères donnés
        double ageScore = calculateAgeScore(user.getAge());
        double employmentScore = calculateEmploymentScore(user.getJob());
        double creditScore = compteBancaire.getScoreCompte();

        // Poids des critères
        double w1 = 0.3;
        double w2 = 0.4;
        double w3 = 0.3;

        // Calcul du score total
        double score = w1 * ageScore + w2 * employmentScore + w3 * creditScore;

        return score;
    }

    public String evaluateRisk(User user, CompteBancaire compteBancaire, DemandeCredit demandeCredit) {
        // Modèle de risque linéaire hypothétique
        double alpha = 0.6;
        double beta = 0.2;
        double gamma = 0.1;

        // Calcul du risque
        double risk = alpha * compteBancaire.getScoreCompte() + beta * compteBancaire.getNombreDeRetardDePaiement() + gamma * demandeCredit.getMontant() / (user.getRevenuMensuel() * 12);

        // Déterminer le niveau de risque en fonction de la valeur de risk
        if (risk < 300) {
            return "Faible";
        } else if (risk < 500) {
            return "Modéré";
        } else {
            return "Élevé";
        }
    }

    public String approveCredit(User user, CompteBancaire compteBancaire, DemandeCredit demandeCredit) {
        double score = calculateScore(user, compteBancaire, demandeCredit);
        String riskLevel = evaluateRisk(user, compteBancaire, demandeCredit);

        // Logique de décision pour approuver ou non le crédit
        if (score > 70 && riskLevel.equals("Faible")) {
            return "Crédit approuvé";
        } else if (score > 60 && riskLevel.equals("Modéré")) {
            return "Crédit approuvé avec conditions";
        } else {
            return "Crédit refusé";
        }
    }

    private double calculateAgeScore(Integer age) {
        return 100 - age;
    }

    private double calculateEmploymentScore(TJob job) {
        switch (job) {
            case INGENIEUR:
                return 90;
            case MEDECIN:
                return 95;
            case AVOCAT:
                return 85;
            case ENSEIGNANT:
                return 80;
            case COMPTABLE:
                return 86;
            case INFORMATICIEN:
                return 88;
            case CONSULTANT:
                return 75;
            case AGENT_IMMOBILIER:
                return 70;
            case ARTISTE:
                return 60;
            case ELECTRICIEN:
                return 71;
            case PLOMBIER:
                return 72;
            case AGRICULTEUR:
                return 65;
            case CHAUFFEUR:
                return 59;
            case VENDEUR:
                return 65;
            case GESTIONNAIRE:
                return 74;
            case ENTREPRENEUR:
                return 80;
            case ARCHITECTE:
                return 85;
            case ANALYSTE_FINANCIER:
                return 90;
            case BANQUIER:
                return 88;
            case EMPLOYE_ADMINISTRATIF:
                return 90;
        }
        return 0;
    }
}

    /*private double calculateLoanAmountScore ( double loanAmount, double annualIncome){
            // Implémentation du score basé sur le montant du prêt par rapport au revenu
            // Exemple simplifié : score plus élevé pour des ratios plus faibles
            return 100 - (loanAmount / annualIncome) * 100;
        }*/

