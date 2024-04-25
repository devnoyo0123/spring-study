package com.example.mildangbespringstudy.chap01.studyActivityInstance.dataaccess;

import com.example.mildangbespringstudy.chap01.domain.StudyActivityInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SAIJpaRepository extends JpaRepository<StudyActivityInstance, UUID> {
}
