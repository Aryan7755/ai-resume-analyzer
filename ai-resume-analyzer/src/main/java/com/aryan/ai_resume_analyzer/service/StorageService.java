package com.aryan.ai_resume_analyzer.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.PublicKey;
import java.util.UUID;

@Service
public class StorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    private Path root;
    @PostConstruct
    public void init() {
        root = Paths.get(uploadDir);
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public String store(MultipartFile file){
        //Generating the unique name
        String uniqueFileName = UUID.randomUUID().toString()+"-"+file.getOriginalFilename();
        //Determining the full destination path
        Path destinationFile = this.root.resolve(Paths.get(uniqueFileName)).normalize().toAbsolutePath();
        // 3. Physical Save: Stream the file bytes to the destination
        try {
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }
}
