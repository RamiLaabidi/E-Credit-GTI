package com.tn.gti.demandeDeCredit.Entities;

import com.tn.gti.demandeDeCredit.Entities.Enum.TypeC;
import com.tn.gti.demandeDeCredit.Entities.Enum.TypeU;
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
public class DemandeCredit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idD;
    Integer numD;
    Date date;
    @Enumerated(EnumType.STRING)
    TypeC typeCredit;
    @Enumerated(EnumType.STRING)
    TypeU unite;
    Float montant;
    Integer duree;
    Float interet;

    Long idU;

    @OneToOne
    ContratCredit contratCredit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="demandeCredit")
    private Set<PieceJointe> pieceJointes;


    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Garantie> garanties;







}
