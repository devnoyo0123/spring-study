package com.example.mildangbespringstudy.samplecrud.controller;

import com.example.mildangbespringstudy.samplecrud.application.CommentApplicationService;
import com.example.mildangbespringstudy.samplecrud.application.dto.CommentCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CommentController {

    private final CommentApplicationService commentApplicationService;

    public CommentController(CommentApplicationService commentApplicationService) {
        this.commentApplicationService = commentApplicationService;
    }

    @PostMapping("posts/{postId}/comments")
    public Long createComment(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequest request
    ) {
      return commentApplicationService.createComment(postId, request).id();
    }
}
