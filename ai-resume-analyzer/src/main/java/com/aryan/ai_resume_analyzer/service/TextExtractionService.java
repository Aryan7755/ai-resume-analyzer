package com.aryan.ai_resume_analyzer.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class TextExtractionService {
    private final Tika tika = new Tika();

    public String extractText(String filePath){
    try{
        File file = new File(filePath);
        String text= tika.parseToString(file);
        if (text == null || text.trim().length() < 10) {
            return "ERROR_IMAGE_ONLY";
        }
        return text;
    } catch ( TikaException e) {
        // 3. If it fails, we return a specific error flag
        if (e.getMessage().toLowerCase().contains("password") ||
                e.getMessage().toLowerCase().contains("encrypted")) {
            return "ERROR_ENCRYPTED";
        }
        return "EXTRACTION_ERROR: " + e.getMessage();
    } catch (IOException e) {
        return "EXTRACTION_ERROR: " + e.getMessage();
    }

    }

}
