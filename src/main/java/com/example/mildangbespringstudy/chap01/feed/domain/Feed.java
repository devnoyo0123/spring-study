package com.example.mildangbespringstudy.chap01.feed.domain;

import com.example.mildangbespringstudy.chap01.domain.StudyModuleInstance;
import com.example.mildangbespringstudy.chap01.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "feed")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyModuleInstance> studyModuleInstanceList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    public void addStudyModuleInstance(StudyModuleInstance studyModuleInstance) {
        studyModuleInstanceList.add(studyModuleInstance);
        studyModuleInstance.setFeed(this);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
