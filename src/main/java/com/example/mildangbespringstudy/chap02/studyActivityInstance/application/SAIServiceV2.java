package com.example.mildangbespringstudy.chap02.studyActivityInstance.application;


import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyModuleInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyUnitInstanceV2;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.application.dto.UpdateAnswerRequestV2;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.dataaccess.SAIJpaRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SAIServiceV2 {
    private final SAIJpaRepositoryV2 repository;

    @Transactional
    public StudyActivityInstanceV2 updateAnswer(UUID id, UpdateAnswerRequestV2 request) {
        StudyActivityInstanceV2 sai = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid StudyActivityInstance ID: " + id));

        sai.setUserAnswer(request.userAnswer());
        sai.setIsCorrect(sai.getAnswer().equals(request.userAnswer()));
        StudyUnitInstanceV2 sui = sai.getStudyUnitInstanceV2();
        StudyModuleInstanceV2 smi = sui.getStudyModuleInstanceV2();
        sui.calculateCompleteRate();
        smi.calculateCompleteRate();
        return repository.save(sai);
    }
}
