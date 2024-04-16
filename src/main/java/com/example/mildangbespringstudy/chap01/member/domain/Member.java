package com.example.mildangbespringstudy.chap01.member.domain;

import com.example.mildangbespringstudy.chap01.feed.domain.Feed;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "member")
    List<Feed> feedList = new ArrayList<>();
}
