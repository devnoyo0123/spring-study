package com.example.mildangbespringstudy.chap02.studyActivityInstance.dataaccess;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SAIJpaRepositoryV2 extends JpaRepository<StudyActivityInstanceV2, UUID> {
}
