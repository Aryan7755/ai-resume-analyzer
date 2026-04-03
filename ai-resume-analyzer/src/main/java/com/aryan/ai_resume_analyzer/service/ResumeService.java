package com.aryan.ai_resume_analyzer.service;

import com.aryan.ai_resume_analyzer.model.Resume;
import com.aryan.ai_resume_analyzer.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ParserService parserService;

    public Resume uploadAndSave(MultipartFile file) {
        // 1. Save to physical disk using our helper service
        String filePath = fileStorageService.storeFile(file);

        String rawText = parserService.parsePdf(filePath);

        System.out.println("========================================");
        System.out.println("EXTRACTED TEXT FOR: " + file.getOriginalFilename());
        System.out.println("----------------------------------------");
        System.out.println(rawText);
        System.out.println("========================================");

        Resume resume = new Resume();
        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath);
        resume.setStatus("EXTRACTED");

        // 3. Save to MySQL and return the object
        return resumeRepository.save(resume);
    }
}
