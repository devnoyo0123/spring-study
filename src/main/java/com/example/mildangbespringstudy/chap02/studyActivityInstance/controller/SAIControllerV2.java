package com.example.mildangbespringstudy.chap02.studyActivityInstance.controller;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.SAIDto;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.application.SAIServiceV2;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.application.dto.UpdateAnswerRequestV2;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/study-activity-instances/v2")
public class SAIControllerV2 {

    private final SAIServiceV2 service;


    @Operation(summary = "TODO: Implement this API")
    @PutMapping("/{id}/answer")
    public ResponseEntity<SAIDto> updateAnswer(@PathVariable UUID id, @RequestBody UpdateAnswerRequestV2 request) {
        StudyActivityInstanceV2 sai = service.updateAnswer(id, request);
        return ResponseEntity.ok(SAIDto.of(sai.getId(), sai.getAnswer(), sai.getUserAnswer(), sai.getIsCorrect()));
    }
}
