package com.example.mildangbespringstudy.samplecrud.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    public static Comment of(Long id, String content, String createdBy) {
        Comment comment = new Comment();
        comment.id = id;
        comment.content = content;
        comment.createdBy = createdBy;
        return comment;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}