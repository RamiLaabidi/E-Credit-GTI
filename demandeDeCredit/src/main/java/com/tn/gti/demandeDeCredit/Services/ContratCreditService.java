package com.tn.gti.demandeDeCredit.Services;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;
import com.tn.gti.demandeDeCredit.Entities.ContratCredit;
import com.tn.gti.demandeDeCredit.Entities.DemandeCredit;
import com.tn.gti.demandeDeCredit.Entities.Echeance;
import com.tn.gti.demandeDeCredit.Entities.Enum.TypeU;
import com.tn.gti.demandeDeCredit.Repositories.IContratCreditRepository;
import com.tn.gti.demandeDeCredit.Repositories.IDemandeCreditRepository;
import com.tn.gti.demandeDeCredit.Repositories.IEcheanceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

@Service
@RequestMapping("/ContratCredit")
@RequiredArgsConstructor
public class ContratCreditService implements IContratCreditService {

    @Autowired
    private final IContratCreditRepository iContratCreditRepository;
    private final IDemandeCreditRepository iDemandeCreditRepository;
    private final IEcheanceRepository iEcheanceRepository;

    @Override
    public ContratCredit creerContratEtAffecterADemande(Long idDemande, ContratCredit contratCredit) {
        // Récupérer la demande de crédit depuis la base de données
        Optional<DemandeCredit> demandeOptional = iDemandeCreditRepository.findById(idDemande);
        if (demandeOptional.isEmpty()) {
            throw new EntityNotFoundException("Demande de crédit non trouvée avec l'ID : " + idDemande);
        }
        DemandeCredit demandeCredit = demandeOptional.get();
        // Affecter la demande de crédit au contrat
        contratCredit.setDemandeCredit(demandeCredit);
        // Enregistrer le contrat en base de données
        return iContratCreditRepository.save(contratCredit);
    }

    @Override
    public ContratCredit updateContratCredit(ContratCredit contratCredit) {

        return iContratCreditRepository.save(contratCredit);
    }

    @Override
    public Boolean deleteContratCredit(Long idDoss) {
        Optional<ContratCredit> ContratCreditOptional = iContratCreditRepository.findById(idDoss);
        if (ContratCreditOptional.isPresent()) {
            iContratCreditRepository.deleteById(idDoss);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
    }

    @Override
    public List<ContratCredit> getAllContratCredit() {

        return (List<ContratCredit>) iContratCreditRepository.findAll();

    }

    @Override
    public ContratCredit getContratCreditById(Long idDoss) {
        return iContratCreditRepository.findById(idDoss).orElse(null);

    }


    @Override
    public byte[] generateContratPdf(Long idContrat) throws IOException {
        ContratCredit contrat = iContratCreditRepository.findById(idContrat)
                .orElseThrow(() -> new NotFoundException("Contrat non trouvé avec l'ID : " + idContrat));

        // Générer le PDF avec les données du contrat
        byte[] pdfBytes = generatePdfForContrat(contrat);

        return pdfBytes;
    }

    @Override
    public byte[] generatePdfForContrat(ContratCredit contratCredit) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);

            // Ajouter le contenu structuré du contrat
            contentStream.showText("Contrat de Crédit");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("ID Contrat: " + contratCredit.getIdC());
            contentStream.newLine();
            contentStream.showText("ID Client: " + contratCredit.getIdC());
            contentStream.newLine();

            contentStream.showText("Signature: " + contratCredit.getSignature());
            contentStream.newLine();
            contentStream.showText("Conditions: " + contratCredit.getCondition());
            contentStream.newLine();
            contentStream.showText("Date: " + contratCredit.getDateC().toString());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Détails du Crédit");
            contentStream.newLine();
            contentStream.showText("Montant: " + contratCredit.getDemandeCredit().getMontant());
            contentStream.newLine();
            contentStream.showText("Durée: " + contratCredit.getDemandeCredit().getDuree());
            contentStream.newLine();
            contentStream.showText("Intérêt: " + contratCredit.getDemandeCredit().getInteret());
            contentStream.endText();
        }
        // Create a rectangle
        // Adding a table-like structure (bounding box) for "Table de remboursement"
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
            contentStream.setStrokingColor(Color.BLACK);
            contentStream.setLineWidth(1);
            float startX = 25;
            float startY = 650;
            float width = 550;
            float height = 100;
            contentStream.addRect(startX, startY, width, height);
            contentStream.stroke();

            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER_BOLD, 16);
            contentStream.newLineAtOffset(startX + width / 2 - 50, startY + height - 20);  // Centering text horizontally
            contentStream.showText("Contrat de Crédit");
            contentStream.endText();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    @Override
    public void processWordDocument(InputStream inputStream, String outputPdfPath, Map<String, String> replacementMap) throws Exception {
        // Ouvrir le document Word
        XWPFDocument document = new XWPFDocument(inputStream);

        // Parcourir tous les paragraphes et remplacer les placeholders généraux
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                    for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
                        text = text.replace(entry.getKey(), entry.getValue());
                    }
                    run.setText(text, 0);
                }
            }
        }

        // Remplacer les placeholders spécifiques pour montant, intérêt et durée
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                    if (text.contains("{{MONTANT}}")) {
                        text = text.replace("{{MONTANT}}", replacementMap.getOrDefault("{{MONTANT}}", ""));
                        run.setText(text, 0);
                    }
                    if (text.contains("{{DUREE}}")) {
                        text = text.replace("{{DUREE}}", replacementMap.getOrDefault("{{DUREE}}", ""));
                        run.setText(text, 0);
                    }
                    if (text.contains("{{INTERET}}")) {
                        text = text.replace("{{INTERET}}", replacementMap.getOrDefault("{{INTERET}}", ""));
                        run.setText(text, 0);
                    }
                }
            }
        }

        // Sauvegarder le document Word modifié
        FileOutputStream out = new FileOutputStream("updated_document.docx");
        document.write(out);
        out.close();

        // Convertir le document Word en PDF
       // convertToPdf("updated_document.docx", outputPdfPath);
    }


   @Override
   public ContratCredit assignEcheanceToContrat(Long contratId, Long echeanceId) {
       Optional<ContratCredit> optionalContratCredit = iContratCreditRepository.findById(contratId);
       Optional<Echeance> optionalEcheance = iEcheanceRepository.findById(echeanceId);

       if (optionalContratCredit.isPresent() && optionalEcheance.isPresent()) {
           ContratCredit contratCredit = optionalContratCredit.get();
           Echeance echeance = optionalEcheance.get();

           echeance.setContratCredit(contratCredit);
           contratCredit.getEcheances().add(echeance);

           iContratCreditRepository.save(contratCredit);

           return contratCredit;
       } else {
           throw new IllegalArgumentException("ContratCredit or Echeance not found with given IDs");
       }
   }


@Override
    public Set<Echeance> genererEcheances(float montant, int duree, float interet, TypeU unite, Long contratId) {
        // Calcul du taux d'intérêt annuel, trimestriel et semestriel à partir du taux mensuel
        float interetAnnuelle = (float) (Math.pow(1 + interet, 12) - 1);
        float interetTrimestriel = (float) (Math.pow(1 + interet, 3) - 1);
        float interetSemestrielle = (float) (Math.pow(1 + interet, 6) - 1);

        // Ajustement du taux d'intérêt et du nombre d'échéances en fonction de l'unité
        switch (unite) {
            case MENSUELLE:
                // Le taux d'intérêt est déjà mensuel, aucune conversion nécessaire
                break;
            case TRIMESTRIELLE:
                interet = interetTrimestriel;
                duree /= 3; // Convertir le nombre d'échéances en trimestrielles
                break;
            case SEMESTRIELLE:
                interet = interetSemestrielle;
                duree /= 6; // Convertir le nombre d'échéances en semestrielles
                break;
            case ANNUELLE:
                interet = interetAnnuelle;
                duree /= 12; // Convertir le nombre d'échéances en annuelles
                break;
        }
        // Calcul de la mensualité en utilisant la formule de l'annuité
        float mensualite = (montant * interet) / (1 - (float) Math.pow(1 + interet, -duree));

        Calendar calendar = Calendar.getInstance();

        // Création des échéances
        Set<Echeance> nouvellesEcheances = new HashSet<>();
        float capitalRestant = montant;

        // Récupérer le contrat correspondant
        ContratCredit contratCredit = iContratCreditRepository.findById(contratId)
                .orElseThrow(() -> new IllegalArgumentException("ContratCredit not found"));

        for (int i = 0; i < duree; i++) {
            Echeance echeance = new Echeance();
            // Définir le dernier jour du mois courant
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            echeance.setDatePaiement(calendar.getTime()); // Date de paiement de l'échéance

            float interetsPayes = capitalRestant * interet; // Intérêts payés pour cette échéance
            float capitalRembourse = mensualite - interetsPayes; // Capital remboursé pour cette échéance

            // Mise à jour du capital restant dû
            capitalRestant -= capitalRembourse;

            echeance.setCapitalRembourse(capitalRembourse); // Capital remboursé
            echeance.setCapitalRestantDu(capitalRestant); // Capital restant dû
            echeance.setInteretsPayes(interetsPayes); // Intérêts payés
            echeance.setMensualite(mensualite); // Mensualité
            echeance.setContratCredit(contratCredit); // Associer l'échéance au contrat

            // Enregistrer l'échéance en base de données
            echeance = iEcheanceRepository.save(echeance);

            nouvellesEcheances.add(echeance);

            // Passage à la même date du mois suivant
            calendar.add(Calendar.MONTH, 1);
        }
        // Ajouter les nouvelles échéances au contrat
        contratCredit.getEcheances().addAll(nouvellesEcheances);
        iContratCreditRepository.save(contratCredit);

        return nouvellesEcheances;
    }
}
