package com.example.mildangbespringstudy.samplecrud.dataaccess;

import com.example.mildangbespringstudy.samplecrud.domain.Team;

import java.util.Optional;

public interface TeamRepository2 {
    Optional<Team> findById(Long id);
}
