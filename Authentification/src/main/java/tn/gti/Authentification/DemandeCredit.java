package tn.gti.Authentification;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandeCredit {

    Integer numD;
    Date date;
    float montant;
    Integer nbrEcheances;
    Float interet;
}
