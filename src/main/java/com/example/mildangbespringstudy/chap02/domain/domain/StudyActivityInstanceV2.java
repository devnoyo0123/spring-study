package com.example.mildangbespringstudy.chap02.domain.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudyActivityInstanceV2 {

    @Id
    private UUID id;

    private Integer answer;

    private Integer userAnswer;

    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudyUnitInstanceV2 studyUnitInstanceV2;

    public static StudyActivityInstanceV2 of(int i) {
        StudyActivityInstanceV2 sai = new StudyActivityInstanceV2();
        sai.setId(UUID.randomUUID());
        sai.isCorrect = false;
        sai.setAnswer(i);
        return sai;
    }
}
