package com.tn.gti.demandeDeCredit.Repositories;

import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IDemandeCreditRepository extends CrudRepository<DemandeCredit, Long> {
    List<DemandeCredit> findAllByIdU(Long idU);
}
