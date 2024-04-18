package com.example.mildangbespringstudy.samplecrud.application;

import com.example.mildangbespringstudy.samplecrud.application.dto.CommentCreateRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.CommentResponse;
import com.example.mildangbespringstudy.samplecrud.dataaccess.CommentJpaRepository;
import com.example.mildangbespringstudy.samplecrud.dataaccess.PostJpaRepository;
import com.example.mildangbespringstudy.samplecrud.domain.Comment;
import com.example.mildangbespringstudy.samplecrud.domain.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CommentApplicationService {
    private final CommentJpaRepository commentRepository;

    private final PostJpaRepository postRepository;

    public CommentApplicationService(CommentJpaRepository commentRepository, PostJpaRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentResponse createComment(Long postId, CommentCreateRequest request) {
        Post postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        Comment commentEntity = Comment.of(null, request.content(), request.createdBy());
        commentEntity.setPost(postEntity);
        postEntity.addComment(commentEntity);
//        commentRepository.save(commentEntity);
        return CommentResponse.of(commentEntity);
    }
}
