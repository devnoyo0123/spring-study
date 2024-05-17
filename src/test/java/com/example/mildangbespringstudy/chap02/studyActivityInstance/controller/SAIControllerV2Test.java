package com.example.mildangbespringstudy.chap02.studyActivityInstance.controller;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyUnitInstanceV2;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.SAIDto;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.application.SAIServiceV2;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.application.dto.UpdateAnswerRequestV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SAIControllerV2.class)
class SAIControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SAIServiceV2 service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[성공] 정답을 업데이트하고, 정오답여부를 업데이트합니다")
    void updateAnswer() throws Exception {
        UUID id = UUID.fromString("4ea0106c-8755-4868-96f4-3e7fa23283a3");

        StudyActivityInstanceV2 sai = new StudyActivityInstanceV2();
        sai.setId(id);
        sai.setAnswer(1);
        sai.setUserAnswer(1);
        sai.setIsCorrect(true);
        StudyUnitInstanceV2 sui = new StudyUnitInstanceV2();
        sai = new StudyActivityInstanceV2();

        sai.setStudyUnitInstanceV2(sui);

        UpdateAnswerRequestV2 request = new UpdateAnswerRequestV2(1);

        given(service.updateAnswer(any(UUID.class), any(UpdateAnswerRequestV2.class))).willReturn(sai);

        mockMvc.perform(put("/study-activity-instances/v2/{id}/answer", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new SAIDto(sai.getId(), sai.getAnswer(), sai.getUserAnswer(), sai.getIsCorrect()))));
    }

    @Test
    @DisplayName("[실패] 잘못된 ID로 정답을 업데이트하려고 시도하면 BadRequest를 반환합니다")
    void updateAnswer_InvalidId() throws Exception {
        UUID id = UUID.fromString("4ea0106c-8755-4868-96f4-3e7fa23283a3");

        UpdateAnswerRequestV2 request = new UpdateAnswerRequestV2(1);

        given(service.updateAnswer(any(UUID.class), any(UpdateAnswerRequestV2.class)))
                .willThrow(new IllegalArgumentException("Invalid StudyActivityInstance ID: " + id));

        mockMvc.perform(put("/study-activity-instances/v2/{id}/answer", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


}