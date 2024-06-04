package com.example.mildangbespringstudy.chap02.domain.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "study_unit_instance_v2", indexes = {
        @Index(name = "study_unit_instance_v2_study_module_instance_id_idx", columnList = "study_module_instance_id")
})
public class StudyUnitInstanceV2 {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    private int completeRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_module_instance_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StudyModuleInstanceV2 studyModuleInstanceV2;

    @OneToMany(mappedBy = "studyUnitInstanceV2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyActivityInstanceV2> studyActivityInstanceV2List = new ArrayList<>();

    public static StudyUnitInstanceV2 of() {
        StudyUnitInstanceV2 sui = new StudyUnitInstanceV2();
        sui.setId(UUID.randomUUID());
        sui.setCompleteRate(0);

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
