package com.example.mildangbespringstudy.samplecrud.application.dto;

import com.example.mildangbespringstudy.samplecrud.domain.SampleMember;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class TeamResponse {
    private Long id;
    private String name;
    private List<MemberResponse> members;

    public static TeamResponse of(Long id, String name, List<SampleMember> members) {
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.id = id;
        teamResponse.name = name;
        teamResponse.members = MemberResponse.ofList(members);
        return teamResponse;
    }
}
