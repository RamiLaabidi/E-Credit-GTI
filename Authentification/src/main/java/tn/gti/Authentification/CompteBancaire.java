package tn.gti.Authentification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompteBancaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompte;
    private String numeroCompte;
    private float solde;
    private Date dateOverture;
    @Enumerated(EnumType.STRING)
    private TCompte typeCompte;
    private float montantRouge;
    private float penaliteDeRetardDePaiement; // Pénalité de retard de paiement
    private int nombreDeRetardDePaiement; // Nombre de retards de paiement
    private float scoreCompte; // Score pour le compte, utilisé pour évaluer le crédit
    private Etat etatCompte;



    @ManyToOne
    private User user;
}
