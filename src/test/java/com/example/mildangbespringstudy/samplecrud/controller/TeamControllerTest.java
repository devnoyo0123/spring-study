package com.example.mildangbespringstudy.samplecrud.controller;

import com.example.mildangbespringstudy.samplecrud.application.TeamService;
import com.example.mildangbespringstudy.samplecrud.application.dto.MemberCreateRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamCreateRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamResponse;
import com.example.mildangbespringstudy.samplecrud.domain.SampleMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * spring framework에서 제공하는 @MockBean 어노테이션을 사용하여 Mock 객체를 생성합니다.
     * bean을 모킹할 수 있습니다.
     */
    @MockBean
    private TeamService teamService;

    @Test
    void createTeam() throws Exception {
        // given
        TeamCreateRequest teamCreateRequest = new TeamCreateRequest("팀 이름",
                new ArrayList<>(List.of(new MemberCreateRequest("이름"))));

        TeamResponse teamResponse = TeamResponse.of(1L, "팀 이름", new ArrayList<>(List.of(new SampleMember(1L,"이름"))));
        given(teamService.createTeam(any())).willReturn(teamResponse);

        // when
        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("팀 이름"))
                .andExpect(jsonPath("$.members[0].id").value(1L))
                .andExpect(jsonPath("$.members[0].name").value("이름")
        );
    }

    @DisplayName("name이 없는 경우 400 Bad Request를 반환한다")
    @Test
    void createTeam_failed_when_name_null() throws Exception {
        // given
        TeamCreateRequest teamCreateRequest = new TeamCreateRequest(null,
                new ArrayList<>(List.of(new MemberCreateRequest("이름"))));

        TeamResponse teamResponse = TeamResponse.of(1L, "팀 이름", new ArrayList<>(List.of(new SampleMember(1L,"이름"))));
        given(teamService.createTeam(any())).willReturn(teamResponse);

        // when
        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamCreateRequest)))
                .andExpect(status().isBadRequest()
        );

    }



}