package com.tn.gti.demandeDeCredit.Entities;

import com.tn.gti.demandeDeCredit.Entities.Enum.DStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DossierCredit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idDoss;
    Date dateCréation;
    Date dateMiseAJour;
    @Enumerated(EnumType.STRING)
    DStatus statut;
    String notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="dossierCredit")
    private Set<DemandeCredit> demandeCredits;


}
