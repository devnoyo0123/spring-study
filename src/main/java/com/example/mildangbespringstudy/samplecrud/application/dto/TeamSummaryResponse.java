package com.example.mildangbespringstudy.samplecrud.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamSummaryResponse {
    private Long id;
    private String name;

    public static TeamSummaryResponse of(Long id, String name) {
        TeamSummaryResponse teamResponse = new TeamSummaryResponse();
        teamResponse.id = id;
        teamResponse.name = name;
        return teamResponse;
    }
}
