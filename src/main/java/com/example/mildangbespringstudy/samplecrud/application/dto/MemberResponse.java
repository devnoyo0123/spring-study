package com.example.mildangbespringstudy.samplecrud.application.dto;

import com.example.mildangbespringstudy.samplecrud.domain.SampleMember;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;

    public static MemberResponse of(Long id, String name) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.id = id;
        memberResponse.name = name;
        return memberResponse;
    }

    public static List<MemberResponse> ofList(List<SampleMember> members) {
        return members.stream()
                .map(member -> MemberResponse.of(member.getId(), member.getName()))
                .toList();
    }
}
