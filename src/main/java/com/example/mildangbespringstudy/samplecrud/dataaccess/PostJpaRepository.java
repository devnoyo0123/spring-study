package com.example.mildangbespringstudy.samplecrud.dataaccess;

import com.example.mildangbespringstudy.samplecrud.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
