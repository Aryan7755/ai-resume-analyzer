package com.aryan.ai_resume_analyzer.controller;

import com.aryan.ai_resume_analyzer.enums.Status;
import com.aryan.ai_resume_analyzer.model.Resume;
import com.aryan.ai_resume_analyzer.repository.ResumeRepository;
import com.aryan.ai_resume_analyzer.service.StorageService;
import com.aryan.ai_resume_analyzer.service.TextExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    @Autowired
    private final StorageService storageService;
    @Autowired
    private final ResumeRepository resumeRepository;
    @Autowired
    private final TextExtractionService extractionService;

    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes(){
        return ResponseEntity.ok(resumeRepository.findAll());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file){
        try {
            if(file.isEmpty()){
                return ResponseEntity.badRequest().body("Please upload a file.");
            }
            String contentType = file.getContentType();
            if(contentType==null || (!contentType.equals("application/pdf") &&
                    !contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
                return ResponseEntity.badRequest().body("Only PDF and DOCX files are allowed.");
            };
            long maxSize = 5 * 1024 * 1024; // 5MB in bytes
            if (file.getSize() > maxSize) {
                return ResponseEntity.badRequest().body("File size exceeds 5MB limit.");
            }
            //save file to disk
            String uniqueName = storageService.store(file);
            //map the file data to resume entity
            Resume resume = new Resume();
            resume.setFileName(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setFilePath("uploads/" + uniqueName);
            resume.setStatus(Status.UPLOADED);
            //save to mysql db
            resumeRepository.save(resume);
            String absolutePath = "uploads/"+uniqueName;
            String extractedContent = extractionService.extractText(absolutePath);
            resume.setRawText(extractedContent);
            if (extractedContent.equals("ERROR_ENCRYPTED")) {
                resume.setStatus(Status.ERROR_ENCRYPTED);
            } else if (extractedContent.equals("ERROR_IMAGE_ONLY")) {
                resume.setStatus(Status.ERROR_IMAGE_ONLY);
            } else if (extractedContent.startsWith("EXTRACTION_ERROR")) {
                resume.setStatus(Status.ERROR);
            } else {
                resume.setStatus(Status.EXTRACTED);
            }
            resumeRepository.save(resume);
            return ResponseEntity.ok("Resume uploaded successfully: " + uniqueName);
        }
        catch (Exception e) {
            // Return a 500 Internal Server Error with the reason
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

}
