package com.tn.gti.demandeDeCredit.Repositories;

import com.tn.gti.demandeDeCredit.Entities.Echeance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEcheanceRepository extends JpaRepository<Echeance, Long> {
    // Additional query methods can be defined here if needed
}
