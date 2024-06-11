package tn.gti.Authentification;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "\"users\"")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idU;
    @Enumerated(EnumType.STRING)
    ROLE role;
    String nom;
    String prenom;
    Date dateDeNaissance;
    @Enumerated(EnumType.STRING)
    TypeSF situationFamiliale;
    String email;
    File photo;
    String numeroDeCompte;
    Date dateOuvertureCompte;
    Long numCin;
    String motDePasse;

}
