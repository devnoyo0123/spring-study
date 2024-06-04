package com.example.mildangbespringstudy.chap02.studyActivityInstance.application;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;

public class SAIFixture {

    public static StudyActivityInstanceV2 createStudyActivityInstance(int answer) {
        return StudyActivityInstanceV2.of(answer);
    }
}
