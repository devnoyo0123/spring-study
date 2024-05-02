package com.example.mildangbespringstudy.chap02.member.domain;

import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
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
public class MemberV2 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "memberV2")
    List<FeedV2> feedList = new ArrayList<>();
}
