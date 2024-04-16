package com.example.mildangbespringstudy.chap01.domain;

import jakarta.persistence.*;
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
public class StudyActivityInstance {

    @Id
    private UUID id;

    private Integer answer;

    private Integer userAnswer;

    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudyUnitInstance studyUnitInstance;

    public static StudyActivityInstance of(int i) {
        StudyActivityInstance sai = new StudyActivityInstance();
        sai.setId(UUID.randomUUID());
        sai.isCorrect = false;
        sai.setAnswer(i);
        return sai;
    }
}
