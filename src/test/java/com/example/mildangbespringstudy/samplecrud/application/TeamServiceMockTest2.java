package com.example.mildangbespringstudy.samplecrud.application;

import com.example.mildangbespringstudy.samplecrud.application.dto.*;
import com.example.mildangbespringstudy.samplecrud.dataaccess.TeamRepository;
import com.example.mildangbespringstudy.samplecrud.dataaccess.TeamRepository2;
import com.example.mildangbespringstudy.samplecrud.domain.SampleMember;
import com.example.mildangbespringstudy.samplecrud.domain.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TeamServiceMockTest2 {

    private final TeamService teamService;

    private final TeamRepository teamRepository;
    private final TeamRepository2 teamRepository2;

    public TeamServiceMockTest2() {

        this.teamRepository = mock(TeamRepository.class);
        this.teamRepository2 = mock(TeamRepository2.class);

        this.teamService = new TeamService(
                teamRepository2,
                teamRepository
        );
    }



    @Test
    public void createTeamTest() {
        // given
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(
                "이름"
        );
        TeamCreateRequest teamCreateRequest = new TeamCreateRequest(
                "팀 이름",
                List.of(memberCreateRequest)
        );
        given(teamRepository.save(any())).willReturn(
                new Team(1L, "팀 이름", new ArrayList<>(List.of(new SampleMember(1L, "이름"))) ));

        // when
        TeamResponse teamResponse = teamService.createTeam(teamCreateRequest);

        // then
        assertEquals(teamCreateRequest.getName(),teamResponse.getName());
        verify(teamRepository, times(1)).save(any());
    }

    @Test
    public void updateTeamTest() {
        // given

        Team team = new Team(
                1L,
                "팀 이름",
                new ArrayList<>(List.of(new SampleMember(1L, "이름")))
        );

        given(teamRepository.findById(any())).willReturn(Optional.of(team));


        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest(
                1L,
                "이름수정"
        );
        TeamUpdateRequest teamUpdateRequest = new TeamUpdateRequest(
                1L,
                "팀 이름수정",
                new ArrayList<>(List.of(memberUpdateRequest))
        );

        // when
        TeamResponse teamResponse = teamService.updateTeam(teamUpdateRequest);

        // then
        assertEquals(teamResponse.getName(), teamUpdateRequest.getName());
        assertEquals(teamResponse.getMembers().get(0).getName(),teamUpdateRequest.getMembers().get(0).getName());
    }
}
