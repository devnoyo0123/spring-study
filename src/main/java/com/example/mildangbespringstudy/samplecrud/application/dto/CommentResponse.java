package com.example.mildangbespringstudy.samplecrud.application.dto;


import com.example.mildangbespringstudy.samplecrud.domain.Comment;

public record CommentResponse(Long id, String content, String createdBy) {
    public static CommentResponse of(Comment commentEntity) {
        return new CommentResponse(
                commentEntity.getId(),
                commentEntity.getContent(),
                commentEntity.getCreatedBy()
        );
    }
}
