package com.example.mildangbespringstudy.samplecrud.application.dto;


import com.example.mildangbespringstudy.samplecrud.domain.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private List<CommentResponse> comments = new ArrayList<>();
    private List<String> tags =new ArrayList<>();

    public static PostResponse of(Long id, String title, String content, String createdBy, List<Comment> comments) {
        PostResponse postResponse = new PostResponse();
        postResponse.id = id;
        postResponse.title = title;
        postResponse.content = content;
        postResponse.createdBy = createdBy;
        postResponse.comments = comments.stream().map(CommentResponse::of).toList();
        postResponse.tags = null;
        return postResponse;
    }
}
