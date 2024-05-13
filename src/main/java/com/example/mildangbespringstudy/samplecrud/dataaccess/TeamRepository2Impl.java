package com.example.mildangbespringstudy.samplecrud.dataaccess;

import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSearchRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSummaryResponse;
import com.example.mildangbespringstudy.samplecrud.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TeamRepository2Impl implements TeamRepository2{
    private final TeamJpaRepository teamJpaRepository;
    private final TeamCustomRepository teamCustomRepository;

    @Override
    public Optional<Team> findById(Long id) {
        return teamJpaRepository.findById(id);
    }

}
