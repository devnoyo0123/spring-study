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
@Table(name = "member_v2")
public class MemberV2 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "memberV2")
    List<FeedV2> feedList = new ArrayList<>();

    public static MemberV2 of(String email, String password, String name) {
        MemberV2 member = new MemberV2();
        member.email = email;
        member.password = password;
        member.name = name;
        return member;
    }

    public void addFeed(FeedV2 feed) {
        feedList.add(feed);
        feed.setMemberV2(this);
    }
}
