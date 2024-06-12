package com.example.mildangbespringstudy.chap02.member.application.dto;

import lombok.Getter;

@Getter
public class MemberDtoV2 {
    private Long id;
    private String email;
    private String name;

    public static MemberDtoV2 of(Long id, String email, String name) {
        MemberDtoV2 memberDtoV2 = new MemberDtoV2();
        memberDtoV2.id = id;
        memberDtoV2.email = email;
        memberDtoV2.name = name;
        return memberDtoV2;
    }
}
