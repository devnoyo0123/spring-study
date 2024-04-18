package com.example.mildangbespringstudy.samplecrud.dataaccess;

import com.example.mildangbespringstudy.samplecrud.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
