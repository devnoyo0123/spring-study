package com.example.mildangbespringstudy.samplecrud.controller;


import com.example.mildangbespringstudy.samplecrud.application.PostApplicationService;
import com.example.mildangbespringstudy.samplecrud.application.dto.PostCreateRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.PostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PostController {

    private final PostApplicationService postApplicationService;

    public PostController(PostApplicationService postApplicationService) {
        this.postApplicationService = postApplicationService;
    }

    @PostMapping("/posts")
    public PostResponse post(@RequestBody PostCreateRequest request) {
        return postApplicationService.createPost(request);
    }
}
