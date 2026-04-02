package com.aryan.ai_resume_analyzer.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime uploadedAt;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;

    // No-args constructor (required by JPA)
    public Resume() {
    }


}