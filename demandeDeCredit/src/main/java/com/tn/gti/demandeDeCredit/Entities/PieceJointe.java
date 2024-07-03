package com.tn.gti.demandeDeCredit.Entities;

import com.tn.gti.demandeDeCredit.Entities.Enum.TypeP;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.Serializable;
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PieceJointe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idP;
    String nomP;
    @Enumerated(EnumType.STRING)
    TypeP typepiece;
    Boolean obligatoire;
    Boolean statut;

    @ManyToOne
    DemandeCredit demandeCredit;
}
