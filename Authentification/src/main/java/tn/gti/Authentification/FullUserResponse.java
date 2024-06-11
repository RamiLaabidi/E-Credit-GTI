package tn.gti.Authentification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserResponse {
    private String name;
    private String prenom;
    List<DemandeCredit> demandeCredits;

}
