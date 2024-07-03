package com.tn.gti.demandeDeCredit;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PdfNumericDataExtractorService {
    List<Double> extractNumericData(MultipartFile file) throws IOException;

}
