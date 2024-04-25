package com.example.mildangbespringstudy.chap01.studyActivityInstance.application.dto;

import java.util.UUID;

public record UpdateAnswerRequest(UUID studyActivityInstanceId, int userAnswer) {
}
