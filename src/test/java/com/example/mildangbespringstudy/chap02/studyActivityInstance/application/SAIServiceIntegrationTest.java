package com.example.mildangbespringstudy.chap02.studyActivityInstance.application;

import com.example.mildangbespringstudy.CleanUp;
import com.example.mildangbespringstudy.IntegrationTest;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyModuleInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyUnitInstanceV2;
import com.example.mildangbespringstudy.chap02.external.sns.SNSService;
import com.example.mildangbespringstudy.chap02.feed.application.FeedServiceV2;
import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
import com.example.mildangbespringstudy.chap02.member.application.MemberServiceV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import com.example.mildangbespringstudy.chap02.studyActivityInstance.application.dto.UpdateAnswerRequestV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class SAIServiceIntegrationTest extends IntegrationTest {

    @Test
    void contextLoads() {

    }

    @Autowired
    private SAIServiceV2 sut;

    @SpyBean
    private SNSService snsService;

    @Autowired
    private MemberServiceV2 memberService;

    @Autowired
    private FeedServiceV2 feedService;

    @Autowired
    private CleanUp cleanUp;

    @AfterEach
    public void tearDown() {
        cleanUp.all();
    }

    @Test
    public void testUpdateAnswer() {
        // given
        FeedV2 feed = FeedV2.of();
        StudyActivityInstanceV2 sai = SAIFixture.createStudyActivityInstance(1);
        StudyUnitInstanceV2 sui = StudyUnitInstanceV2.of();
        StudyModuleInstanceV2 smi = StudyModuleInstanceV2.of();
        sui.addStudyActivityInstance(sai);
        smi.addStudyUnitInstance(sui);

        feed.addStudyModuleInstance(smi);
        feedService.createFeed(feed);

        MemberV2 member = MemberV2.of("nohys@ihateflyingbugs.com", "zirineyo", "yobs");
        memberService.createMember(member.getEmail(), member.getPassword(), member.getName());

        member.addFeed(feed);

        // when
        StudyActivityInstanceV2 updatedSai = sut.updateAnswer(sai.getId(), new UpdateAnswerRequestV2(2));

        // then
        assertThat(updatedSai.getUserAnswer()).isEqualTo(2);
        assertThat(updatedSai.getIsCorrect()).isFalse();
        assertThat(updatedSai.getStudyUnitInstanceV2().getCompleteRate()).isGreaterThan(0);
        assertThat(updatedSai.getStudyUnitInstanceV2().getStudyModuleInstanceV2().getCompleteRate()).isGreaterThan(0);
        verify(snsService).send(any(String.class));
    }
}
