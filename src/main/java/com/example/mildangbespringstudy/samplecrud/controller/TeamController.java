package com.example.mildangbespringstudy.samplecrud.controller;

import com.example.mildangbespringstudy.samplecrud.application.TeamService;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamCreateRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamResponse;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSearchRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSummaryResponse;
import com.example.mildangbespringstudy.samplecrud.domain.Member;
import com.example.mildangbespringstudy.samplecrud.domain.Team;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamCreateRequest request) {
        TeamResponse teamResponse = teamService.createTeam(request);
        return ResponseEntity.ok(teamResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeam(@PathVariable Long id) {
        TeamResponse teamResponse = teamService.getTeamBy(id);
        return ResponseEntity.ok(teamResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryResponse>> searchTeam(TeamSearchRequest request) {
        List<TeamSummaryResponse> teamSummaryResponses = teamService.searchTeam(request);
        return ResponseEntity.ok(teamSummaryResponses);
    }

    @PostMapping("/sample-test")
    public ResponseEntity<Void> generateSampleData() {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            Team team = new Team();
            Faker faker = new Faker();
            team.setName(faker.name().username());
            int memberCount = 5 + random.nextInt(6); // 5~10 사이의 멤버 수

            for (int j = 0; j < memberCount; j++) {
                Member member = new Member();
                member.setName(faker.name().username());
                team.addMember(member);
            }

            teamService.saveTeam(team); // 수정된 부분: TeamService에 saveTeam 메서드 구현 필요
        }

        return ResponseEntity.ok().build();
    }
}