package com.aryan.ai_resume_analyzer.controller;

import com.aryan.ai_resume_analyzer.model.Resume;
import com.aryan.ai_resume_analyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file) {
        // The Controller only handles the HTTP request/response
        Resume savedResume = resumeService.uploadAndSave(file);

        // Return the clean JSON structure requested in your goal
        return ResponseEntity.ok(Map.of(
                "id", savedResume.getId(),
                "filePath", savedResume.getFilePath()
        ));
    }
}
