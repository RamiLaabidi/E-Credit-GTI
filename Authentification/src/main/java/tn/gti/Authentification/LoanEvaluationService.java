package tn.gti.Authentification;

public interface LoanEvaluationService {

     String evaluateRisk( CompteBancaire compteBancaire, DemandeCredit demandeCredit);
     double calculateScore(User user);
     double calculateAgeScore(Integer age);
     double calculateEmploymentScore(EmploymentType employmentType);
     String approveCredit(User user, CompteBancaire compteBancaire, DemandeCredit demandeCredit);


}
