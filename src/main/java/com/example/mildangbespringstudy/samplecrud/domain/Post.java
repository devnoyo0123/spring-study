package com.example.mildangbespringstudy.samplecrud.domain;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String createdBy;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public static Post of(Long id, String title, String content, String createdBy) {
        Post post = new Post();
        post.id = id;
        post.title = title;
        post.content = content;
        post.createdBy = createdBy;
        return post;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment commentEntity) {
        comments.add(commentEntity);
        commentEntity.setPost(this);
    }
}