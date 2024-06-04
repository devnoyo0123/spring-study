package com.example.mildangbespringstudy.chap02.domain.domain;

import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "study_module_instance_v2", indexes = {
        @Index(name = "study_module_instance_v2_feed_id_idx", columnList = "feed_id")
})
public class StudyModuleInstanceV2 {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    private int completeRate;

    @OneToMany(mappedBy = "studyModuleInstanceV2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyUnitInstanceV2> studyUnitInstanceV2List = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private FeedV2 feedV2;

    public static StudyModuleInstanceV2 of() {
        StudyModuleInstanceV2 smi = new StudyModuleInstanceV2();
        smi.setId(UUID.randomUUID());
        smi.setCompleteRate(0);
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
