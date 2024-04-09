package com.example.mildangbespringstudy.samplecrud.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class TeamCreateRequest {
    private String name;
    private List<MemberCreateRequest> members;
}
