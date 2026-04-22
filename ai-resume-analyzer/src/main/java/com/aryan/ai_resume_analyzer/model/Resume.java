package com.aryan.ai_resume_analyzer.model;

import com.aryan.ai_resume_analyzer.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String rawText;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String parsedData;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime uploadedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    private boolean deleted = false;
}