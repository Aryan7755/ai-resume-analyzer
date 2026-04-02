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
    public Resume saveResume(MultipartFile file) throws IOException {
        Resume resume = new Resume();

        // Extract details
        resume.setFileName(file.getOriginalFilename());
        resume.setFileType(file.getContentType());
        resume.setFileData(file.getBytes());

        // Timestamp
        resume.setUploadedAt(LocalDateTime.now());

        // Save to DB
        return resumeRepository.save(resume);
    }
}
