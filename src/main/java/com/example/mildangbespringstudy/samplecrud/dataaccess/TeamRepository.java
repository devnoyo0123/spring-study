package com.example.mildangbespringstudy.samplecrud.dataaccess;

import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSearchRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSummaryResponse;
import com.example.mildangbespringstudy.samplecrud.domain.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
    Team save(Team team);

    Optional<Team> findById(Long id);

    void deleteById(Long id);

    List<TeamSummaryResponse> searchTeam(TeamSearchRequest request);
}
