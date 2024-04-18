package com.example.mildangbespringstudy.samplecrud.application.dto;

public record CommentCreateRequest(
        String content,
        String createdBy
) {
}
