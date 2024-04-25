package com.example.mildangbespringstudy.studyactivityinstance.controller;


import com.example.mildangbespringstudy.domain.StudyActivityInstance;
import com.example.mildangbespringstudy.feed.dataaccess.SAIDto;
import com.example.mildangbespringstudy.studyactivityinstance.application.SAIService;
import com.example.mildangbespringstudy.studyactivityinstance.application.dto.UpdateAnswerRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/study-activity-instances")
public class SAIController {

    private final SAIService service;


    @Operation(summary = "[ㅇ] TODO: Implement this API")
    @PutMapping("/{id}/answer")
    public ResponseEntity<SAIDto> updateAnswer(@PathVariable UUID id, @RequestBody UpdateAnswerRequest request) {
        StudyActivityInstance sai = service.updateAnswer(id, request);
        return ResponseEntity.ok(SAIDto.of(sai.getId(), sai.getAnswer(), sai.getUserAnswer(), sai.getIsCorrect()));
    }
}