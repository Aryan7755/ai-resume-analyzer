package com.aryan.ai_resume_analyzer.controller;

import com.aryan.ai_resume_analyzer.model.Resume;
import com.aryan.ai_resume_analyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, Object> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            Resume savedResume = resumeService.saveResume(file);

            Map<String, Object> response = new HashMap<>();
            response.put("id", savedResume.getId());
            response.put("fileName", savedResume.getFileName());

            return response;

        } catch (Exception e) {
            throw new RuntimeException("File upload failed");
        }
    }
}
