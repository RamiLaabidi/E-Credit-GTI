package com.tn.gti.demandeDeCredit.Entities;

import com.tn.gti.demandeDeCredit.Entities.Enum.TypeG;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Garantie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idG;
    String nature;
    @Enumerated(EnumType.STRING)
    TypeG typeGarantie;
    Float estimationValeur;

    @ManyToMany(mappedBy="garanties", cascade = CascadeType.ALL)
    private Set<DemandeCredit> demandeCreditSet;


}
