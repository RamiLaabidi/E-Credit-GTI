package com.tn.gti.demandeDeCredit.Services;

import com.tn.gti.demandeDeCredit.Entities.ContratCredit;
import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import com.tn.gti.demandeDeCredit.Entities.Echeance;
import com.tn.gti.demandeDeCredit.Entities.Enum.TypeU;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IContratCreditService {
    public ContratCredit creerContratEtAffecterADemande(Long idDemande, ContratCredit contratCredit);

    ContratCredit updateContratCredit(ContratCredit contratCredit);

    Boolean deleteContratCredit(Long idDoss);

    List<ContratCredit> getAllContratCredit();

    ContratCredit getContratCreditById(Long idD);

    byte[] generatePdfForContrat(ContratCredit contrat) throws IOException;

    byte[] generateContratPdf(Long idContrat) throws IOException;

    void processWordDocument(InputStream inputStream, String outputPdfPath, Map<String, String> replacementMap) throws Exception;

    ContratCredit assignEcheanceToContrat(Long contratId, Long echeanceId);
    Set<Echeance> genererEcheances(float montant, int duree, float interet, TypeU unite, Long contratId);
}