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
    private Integer numeroCompte;
    private float solde;
    private Date dateOvertureCompte;
    @Enumerated(EnumType.STRING)
    private TCompte typeCompte;
    private float montantRouge;
    private Integer nombreDeRetardDePaiement;
    @Enumerated(EnumType.STRING)
     private EtatC etatDeCompte;



    @ManyToOne
    private User user;
}
