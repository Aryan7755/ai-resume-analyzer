package com.aryan.ai_resume_analyzer.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ParserService {
    public String parsePdf(String filePath){
        try {
            // Tika is the 'magic' object that detects file types and extracts text
            Tika tika = new Tika();
            File file = new File(filePath);

            // This extracts the text content and ignores the images/formatting
            return tika.parseToString(file);
        } catch (TikaException e) {
            throw new RuntimeException("Failed to extract text from PDF: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("General parser error: " + e.getMessage());
        }
    }
}
