package com.example.mildangbespringstudy.samplecrud.application;

import com.example.mildangbespringstudy.samplecrud.application.dto.*;
import com.example.mildangbespringstudy.samplecrud.dataaccess.TeamRepository;
import com.example.mildangbespringstudy.samplecrud.domain.SampleMember;
import com.example.mildangbespringstudy.samplecrud.domain.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TeamServiceMockTest {

    /**
     * mockito에서 제공하는 @Mock 어노테이션을 사용하여 Mock 객체를 생성합니다.
     * bean을 모킹할 수 있습니다.
     */
    @Mock
    private TeamRepository teamRepository;


    /**
     * mockito에서 제공하는 @InjectMocks 어노테이션을 사용하여 Mock 객체를 주입합니다.
     */
    @InjectMocks
    private TeamService teamService;

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
