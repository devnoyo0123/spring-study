package com.example.mildangbespringstudy.chap01.studyActivityInstance.application;

import com.example.mildangbespringstudy.chap01.domain.StudyActivityInstance;
import com.example.mildangbespringstudy.chap01.domain.StudyModuleInstance;
import com.example.mildangbespringstudy.chap01.domain.StudyUnitInstance;
import com.example.mildangbespringstudy.chap01.studyActivityInstance.application.dto.UpdateAnswerRequest;
import com.example.mildangbespringstudy.chap01.studyActivityInstance.dataaccess.SAIJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SAIService {
    private final SAIJpaRepository repository;

    @Transactional
    public StudyActivityInstance updateAnswer(UUID id, UpdateAnswerRequest request) {
        StudyActivityInstance sai = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid StudyActivityInstance ID: " + id));

        sai.setUserAnswer(request.userAnswer());
        sai.setIsCorrect(sai.getAnswer() == request.userAnswer());
        StudyUnitInstance sui = sai.getStudyUnitInstance();
        StudyModuleInstance smi = sui.getStudyModuleInstance();
        sui.calculateCompleteRate();
        smi.calculateCompleteRate();
        return repository.save(sai);
    }
}
