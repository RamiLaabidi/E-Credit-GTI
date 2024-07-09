package tn.gti.Authentification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanEvaluationRequest {

    private User user;
    private CompteBancaire compteBancaire;
    private DemandeCredit demandeCredit;

}
