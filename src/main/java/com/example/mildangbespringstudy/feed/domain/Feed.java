package com.example.mildangbespringstudy.feed.domain;

import com.example.mildangbespringstudy.domain.StudyModuleInstance;
import com.example.mildangbespringstudy.member.domain.Member;
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
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyModuleInstance> studyModuleInstanceList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void addStudyModuleInstance(StudyModuleInstance studyModuleInstance) {
        studyModuleInstanceList.add(studyModuleInstance);
        studyModuleInstance.setFeed(this);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

