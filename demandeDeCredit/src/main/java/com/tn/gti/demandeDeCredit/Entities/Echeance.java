package com.tn.gti.demandeDeCredit.Entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Echeance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idE;
    Date datePaiement;
    Date datePaiementInitiale;
    Float mensualite;
    Float interetsPayes;
    Float capitalRembourse;
    Float capitalRestantDu;;


    @ManyToOne
    ContratCredit contratCredit;
}
