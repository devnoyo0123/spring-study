package com.example.mildangbespringstudy.chap01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudyUnitInstance {

    @Id
    private UUID id;

    private int completeRate;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudyModuleInstance studyModuleInstance;

    @OneToMany(mappedBy = "studyUnitInstance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyActivityInstance> studyActivityInstanceList = new ArrayList<>();

    public static StudyUnitInstance of(List<StudyActivityInstance> sais) {
        StudyUnitInstance sui = new StudyUnitInstance();
        sui.setId(UUID.randomUUID());
        sui.setCompleteRate(0);
        sui.setStudyActivityInstanceList(sais);
        return sui;
    }

    public void addStudyActivityInstance(StudyActivityInstance sai) {
        studyActivityInstanceList.add(sai);
        sai.setStudyUnitInstance(this);
    }

    public void calculateCompleteRate() {
        int total = studyActivityInstanceList.size();
        int correct = (int) studyActivityInstanceList.stream()
                .filter((sai) -> sai.getUserAnswer() != null)
                .count();
        completeRate = total > 0 ? (int) ((double) correct / total * 100) : 0;
    }
}
