package com.example.mildangbespringstudy.chap02.domain.domain;

import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
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
public class StudyModuleInstanceV2 {

    @Id
    private UUID id;

    private int completeRate;

    @OneToMany(mappedBy = "studyModuleInstanceV2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyUnitInstanceV2> studyUnitInstanceV2List = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private FeedV2 feedV2;

    public static StudyModuleInstanceV2 of(List<StudyUnitInstanceV2> studyUnitInstanceV2List) {
        StudyModuleInstanceV2 smi = new StudyModuleInstanceV2();
        smi.setId(UUID.randomUUID());
        smi.setCompleteRate(0);
        smi.setStudyUnitInstanceV2List(studyUnitInstanceV2List);
        return smi;
    }

    public void setFeedV2(FeedV2 feedV2) {
        this.feedV2 = feedV2;
    }

    public void addStudyUnitInstance(StudyUnitInstanceV2 sui) {
        studyUnitInstanceV2List.add(sui);
        sui.setStudyModuleInstanceV2(this);
    }

    public void calculateCompleteRate() {
        int total = studyUnitInstanceV2List.size();
        int completed = (int) studyUnitInstanceV2List.stream()
                .filter((sui) -> sui.getCompleteRate() == 100)
                .count();
        completeRate = total > 0 ? (int) ((double) completed / total * 100) : 0;
    }
}
