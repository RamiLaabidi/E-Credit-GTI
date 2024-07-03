package com.tn.gti.demandeDeCredit.ocrConfig;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Service
@RequestMapping("/ocr")
@RequiredArgsConstructor
public class OcrService {

    private final Tesseract tesseract;

    public OcrService() {
        this.tesseract = new Tesseract();
        // Configurer le chemin vers les fichiers de langue de Tesseract si nécessaire
        this.tesseract.setDatapath("C:\\Users\\ramil\\Desktop\\GTI\\E-Credit-GTI\\demandeDeCredit\\tessdata"); // Remplacez par le chemin réel
    }

    public String extractDataFromInvoice(File invoiceFile) throws TesseractException {
        // Utiliser Tesseract pour extraire le texte de l'image ou du PDF de la facture
        String extractedText = this.tesseract.doOCR(invoiceFile);
        // Traiter le texte extrait selon vos besoins
        return extractedText;
    }
}