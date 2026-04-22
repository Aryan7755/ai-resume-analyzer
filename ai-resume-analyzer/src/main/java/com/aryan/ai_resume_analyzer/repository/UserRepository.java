package com.aryan.ai_resume_analyzer.repository;

import com.aryan.ai_resume_analyzer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
