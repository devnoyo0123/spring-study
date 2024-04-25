package com.example.mildangbespringstudy.chap01.studyActivityInstance.controller;

import com.example.mildangbespringstudy.chap01.domain.StudyActivityInstance;
import com.example.mildangbespringstudy.chap01.feed.dataaccess.SAIDto;
import com.example.mildangbespringstudy.chap01.studyActivityInstance.application.SAIService;
import com.example.mildangbespringstudy.chap01.studyActivityInstance.application.dto.UpdateAnswerRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/study-activity-instances")
public class SAIController {

    private final SAIService service;


    @Operation(summary = "TODO: Implement this API")
    @PostMapping("/update-answer")
    public ResponseEntity<SAIDto> updateAnswer(@RequestBody UpdateAnswerRequest request) {
        /**
         * TODO
         */
        StudyActivityInstance sai = service.updateAnswer(request);
        return ResponseEntity.ok(SAIDto.of(sai.getId(), sai.getAnswer(), sai.getUserAnswer(), sai.getIsCorrect()));
    }
}
