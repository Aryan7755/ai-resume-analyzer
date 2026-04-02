package com.aryan.ai_resume_analyzer.repository;

import com.aryan.ai_resume_analyzer.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
