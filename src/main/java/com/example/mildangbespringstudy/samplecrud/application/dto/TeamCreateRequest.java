package com.example.mildangbespringstudy.samplecrud.application.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "팀 이름은 필수입니다.")
    private String name;
    private List<MemberCreateRequest> members;
}
