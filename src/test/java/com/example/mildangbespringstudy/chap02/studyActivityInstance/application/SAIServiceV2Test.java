package com.example.mildangbespringstudy.chap02.studyActivityInstance.application;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyModuleInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyUnitInstanceV2;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.application.dto.UpdateAnswerRequestV2;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.dataaccess.SAIJpaRepositoryV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SAIServiceV2Test {

    @Mock
    private SAIJpaRepositoryV2 repository;

    @InjectMocks
    private SAIServiceV2 sut;

    @Test
    @DisplayName("정답을 업데이트하고, 정오답여부를 업데이트합니다")
    void updateAnswer() {
        UUID id = UUID.fromString("4ea0106c-8755-4868-96f4-3e7fa23283a3");
        int userAnswer = 1;
        UpdateAnswerRequestV2 request = new UpdateAnswerRequestV2(userAnswer);

        StudyModuleInstanceV2 smi = new StudyModuleInstanceV2();
        StudyUnitInstanceV2 sui = new StudyUnitInstanceV2();
        StudyActivityInstanceV2 sai = new StudyActivityInstanceV2();

        sui.setStudyModuleInstanceV2(smi);
        sai.setStudyUnitInstanceV2(sui);

        // given: 설정 단계
        sai.setAnswer(userAnswer);
        given(repository.findById(id)).willReturn(Optional.of(sai));
        given(repository.save(any(StudyActivityInstanceV2.class))).willReturn(sai);

        // when: 실행 단계
        sut.updateAnswer(id, request);

        // then: 검증 단계
        verify(repository).findById(id);
        verify(repository).save(sai);
        assertThat(sai.getUserAnswer()).isEqualTo(userAnswer);
        assertThat(sai.getIsCorrect()).isTrue();
    }
}