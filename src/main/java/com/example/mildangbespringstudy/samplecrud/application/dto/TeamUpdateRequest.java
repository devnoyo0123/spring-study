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
public class TeamUpdateRequest {
    private Long id;
    private String name;
    private List<MemberUpdateRequest> members;
}