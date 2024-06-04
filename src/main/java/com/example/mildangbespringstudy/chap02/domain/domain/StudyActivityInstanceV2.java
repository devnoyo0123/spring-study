package com.example.mildangbespringstudy.chap02.domain.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "study_activity_instance_v2", indexes = {
        @Index(name = "study_activity_instance_v2_study_unit_instance_id_idx", columnList = "study_unit_instance_id")
})
public class StudyActivityInstanceV2 {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    private Integer answer;

    private Integer userAnswer;

    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_unit_instance_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StudyUnitInstanceV2 studyUnitInstanceV2;

    public static StudyActivityInstanceV2 of(int i) {
        StudyActivityInstanceV2 sai = new StudyActivityInstanceV2();
        sai.setId(UUID.randomUUID());
        sai.isCorrect = false;
        sai.setAnswer(i);
        return sai;
    }
}
