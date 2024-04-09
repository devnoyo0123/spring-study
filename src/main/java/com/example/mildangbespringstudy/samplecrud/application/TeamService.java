package com.example.mildangbespringstudy.samplecrud.application;

import com.example.mildangbespringstudy.samplecrud.application.dto.*;
import com.example.mildangbespringstudy.samplecrud.dataaccess.TeamRepository;
import com.example.mildangbespringstudy.samplecrud.domain.Member;
import com.example.mildangbespringstudy.samplecrud.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public TeamResponse createTeam(TeamCreateRequest request) {
        // TeamCreateRequest에서 이름을 추출하여 Team 객체 생성
        Team team = new Team();
        team.setName(request.getName());

        // TeamCreateRequest에 포함된 멤버 정보를 사용하여 Member 객체 생성 및 Team에 추가
        if (request.getMembers() != null && !request.getMembers().isEmpty()) {
            for (MemberCreateRequest memberReq : request.getMembers()) {
                Member member = new Member();
                member.setName(memberReq.getName());
                // Team과 Member 연결
                team.addMember(member);
            }
        }
        Team savedTeam = teamRepository.save(team);

        return TeamResponse.of(savedTeam.getId(), savedTeam.getName(), savedTeam.getMembers());
    }

    public TeamResponse getTeamBy(Long id) {
        return teamRepository.findById(id)
                .map(team -> TeamResponse.of(team.getId(), team.getName(), team.getMembers()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
    }

    @Transactional
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public List<TeamSummaryResponse> searchTeam(TeamSearchRequest request) {
        return teamRepository.searchTeam(request);
    }

    @Transactional
    public void saveTeam(Team team) {
        teamRepository.save(team);
    }
}
