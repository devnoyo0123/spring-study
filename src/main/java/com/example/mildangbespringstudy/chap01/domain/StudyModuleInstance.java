package com.example.mildangbespringstudy.chap01.domain;

import com.example.mildangbespringstudy.chap01.feed.domain.Feed;
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
@Table(name = "study_module_instance")
public class StudyModuleInstance {

    @Id
    private UUID id;

    private int completeRate;

    @OneToMany(mappedBy = "studyModuleInstance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyUnitInstance> studyUnitInstanceList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Feed feed;

    public static StudyModuleInstance of(List<StudyUnitInstance> studyUnitInstanceList) {
        StudyModuleInstance smi = new StudyModuleInstance();
        smi.setId(UUID.randomUUID());
        smi.setCompleteRate(0);
        smi.setStudyUnitInstanceList(studyUnitInstanceList);
        return smi;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public void addStudyUnitInstance(StudyUnitInstance sui) {
        studyUnitInstanceList.add(sui);
        sui.setStudyModuleInstance(this);
    }

    public void calculateCompleteRate() {
        int total = studyUnitInstanceList.size();
        int completed = (int) studyUnitInstanceList.stream()
                .filter((sui) -> sui.getCompleteRate() == 100)
                .count();
        completeRate = total > 0 ? (int) ((double) completed / total * 100) : 0;
    }
}
