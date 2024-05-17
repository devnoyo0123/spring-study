package com.example.mildangbespringstudy.samplecrud.application;


import com.example.mildangbespringstudy.chap02.external.sns.SNSService;
import com.example.mildangbespringstudy.samplecrud.application.dto.*;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Bean을 테스트하는 방법
 *
 */
@SpringBootTest
@SqlGroup(
        {
            @Sql(value = "classpath:sql/create-team-service-test.data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/delete-team-service-test.data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
        }
)
class TeamServiceTest {

    /**
     * new로 생성해서 주입하지 않습니다.
     */
    @Autowired
    private TeamService teamService;

    @SpyBean
    private SNSService snsService;


    @Test
    public void junitTest() {
        String expected = "Hello JUnit 5";
        String actual = "Hello JUnit 5";
        assertEquals(expected, actual);
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

        // when
        TeamResponse teamResponse = teamService.createTeam(teamCreateRequest);

        // then
        verify(snsService).send(any(String.class));
        assertEquals(teamCreateRequest.getName(),teamResponse.getName());
    }

    @Test
    void getTeamTest() {
        // given
        Long id = 1L;

        // when
        TeamResponse teamResponse = teamService.getTeamBy(id);

        // then
        assertEquals(id, teamResponse.getId());
    }

    @Test
    void searchTeamTest() {
        // given

        TeamSearchRequest teamSearchRequest = TeamSearchRequest.builder()
                .name(
                        "undefined"
                )
                .page(1)
                .size(10)
                .build();

        // when
        List<TeamSummaryResponse> teamResponses = teamService.searchTeam(teamSearchRequest);

        // then
        assertEquals(0, teamResponses.size());
    }

}