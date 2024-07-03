package com.tn.gti.demandeDeCredit.ocrConfig;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/ocrController")
public class OcrController {
    @Autowired
    private OcrService ocrService;

    @PostMapping("/extract")
    public ResponseEntity<String> extractDataFromInvoice(@RequestParam("file") MultipartFile file) {
        try {
            // Convertir le fichier MultipartFile en File
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);

            // Utiliser le service OCR pour extraire les données
            String extractedData = ocrService.extractDataFromInvoice(convFile);

            return new ResponseEntity<>(extractedData, HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

