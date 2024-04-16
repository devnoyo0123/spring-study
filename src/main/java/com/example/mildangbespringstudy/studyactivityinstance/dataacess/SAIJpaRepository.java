package com.example.mildangbespringstudy.studyactivityinstance.dataacess;


import com.example.mildangbespringstudy.domain.StudyActivityInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SAIJpaRepository extends JpaRepository<StudyActivityInstance, UUID> {
}
