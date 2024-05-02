package com.example.mildangbespringstudy.chap02.domain.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudyUnitInstanceV2 {

    @Id
    private UUID id;

    private int completeRate;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudyModuleInstanceV2 studyModuleInstanceV2;

    @OneToMany(mappedBy = "studyUnitInstanceV2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyActivityInstanceV2> studyActivityInstanceV2List = new ArrayList<>();

    public static StudyUnitInstanceV2 of(List<StudyActivityInstanceV2> sais) {
        StudyUnitInstanceV2 sui = new StudyUnitInstanceV2();
        sui.setId(UUID.randomUUID());
        sui.setCompleteRate(0);
        sui.setStudyActivityInstanceV2List(sais);
        return sui;
    }

    public void addStudyActivityInstance(StudyActivityInstanceV2 sai) {
        studyActivityInstanceV2List.add(sai);
        sai.setStudyUnitInstanceV2(this);
    }

    public void calculateCompleteRate() {
        int total = studyActivityInstanceV2List.size();
        int correct = (int) studyActivityInstanceV2List.stream()
                .filter((sai) -> sai.getUserAnswer() != null)
                .count();
        completeRate = total > 0 ? (int) ((double) correct / total * 100) : 0;
    }
}
