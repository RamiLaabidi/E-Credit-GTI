package tn.gti.Authentification;

public interface LoanEvaluationService {

    public double evaluateRisk(User user, CompteBancaire compteBancaire, DemandeCredit demandeCredit);

}
