package com.example.mildangbespringstudy.studyactivityinstance.application;


import com.example.mildangbespringstudy.domain.StudyActivityInstance;
import com.example.mildangbespringstudy.studyactivityinstance.application.dto.UpdateAnswerRequest;
import com.example.mildangbespringstudy.studyactivityinstance.dataacess.SAIJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SAIService {
    private final SAIJpaRepository repository;

    public StudyActivityInstance updateAnswer(UUID id, UpdateAnswerRequest request) {
        /**
         * TODO: request에 담긴 정보를 이용하여 StudyActivityInstance의 answer와 userAnswer를 업데이트하고, isCorrect를 계산하여 업데이트하는 코드를 작성하세요.
         * isCorrect는 answer와 userAnswer가 같은지 여부에 따라 true 또는 false로 설정합니다.
         * 존재하지 않은 Id의 경우 IllegalArgumentException 익셉션을 던집니다.
         * SMI, SUI 진행율 업데이트해주세요.
         * 업데이트된 StudyActivityInstance를 반환합니다.
         */
        StudyActivityInstance sai = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 SAI 입니다."));

        sai.setUserAnswer(request.userAnswer());
        sai.setIsCorrect(sai.getAnswer().equals(sai.getUserAnswer()));
        sai.getStudyUnitInstance().setCompleteRate(sai.getStudyUnitInstance().getCompleteRate() + 1);
        sai.getStudyUnitInstance().getStudyModuleInstance().setCompleteRate(sai.getStudyUnitInstance().getStudyModuleInstance().getCompleteRate() + 1);
        repository.save(sai);

        return sai;
    }
}
