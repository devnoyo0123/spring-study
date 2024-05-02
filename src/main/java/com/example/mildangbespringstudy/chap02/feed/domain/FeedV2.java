package com.example.mildangbespringstudy.chap02.feed.domain;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyModuleInstanceV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
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
public class FeedV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "feedV2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyModuleInstanceV2> studyModuleInstanceV2List = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberV2 memberV2;

    public void addStudyModuleInstance(StudyModuleInstanceV2 studyModuleInstanceV2) {
        studyModuleInstanceV2List.add(studyModuleInstanceV2);
        studyModuleInstanceV2.setFeedV2(this);
    }

    public void setMemberV2(MemberV2 memberV2) {
        this.memberV2 = memberV2;
    }
}
