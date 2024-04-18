package com.example.mildangbespringstudy.samplecrud.application;


import com.example.mildangbespringstudy.samplecrud.application.dto.PostCreateRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.PostResponse;
import com.example.mildangbespringstudy.samplecrud.dataaccess.PostJpaRepository;
import com.example.mildangbespringstudy.samplecrud.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostApplicationService {

    private final PostJpaRepository postRepository;

    @Transactional
    public PostResponse createPost(PostCreateRequest postCreateRequest) {
        Post post = postRepository.save(Post.of(null, postCreateRequest.title(), postCreateRequest.content(), postCreateRequest.createdBy()));
        return PostResponse.of(post.getId(), post.getTitle(), post.getContent(), post.getCreatedBy(), post.getComments());
    }

}