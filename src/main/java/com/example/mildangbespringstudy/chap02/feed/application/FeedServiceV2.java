package com.example.mildangbespringstudy.chap02.feed.application;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyModuleInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyUnitInstanceV2;
import com.example.mildangbespringstudy.chap02.member.dataaccess.MemberJpaRepositoryV2;
import com.example.mildangbespringstudy.chap02.member.domain.MemberV2;
import com.example.mildangbespringstudy.chap02.feed.application.dto.CreateFeedRequestV2;
import com.example.mildangbespringstudy.chap02.feed.application.dto.FeedSearchRequestV2;
import com.example.mildangbespringstudy.chap02.feed.dataaccess.*;
import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedServiceV2 {

    private final FeedRepositoryV2 feedRepositoryV2;
    private final MemberJpaRepositoryV2 memberRepository;

    @Transactional
    public void createFeeds(CreateFeedRequestV2 request) {
        Random random = new Random();

        for (long memberId = 1; memberId <= request.maxMemberId(); memberId++) {
            long finalMemberId = memberId;
            MemberV2 memberV2 = memberRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("Member not found with id: " + finalMemberId));

            for (int feedCount = 0; feedCount < request.numberOfFeed(); feedCount++) {
                FeedV2 feedV2 = new FeedV2();
                feedV2.setMemberV2(memberV2);
                StudyModuleInstanceV2 smi = new StudyModuleInstanceV2();
                smi.setId(UUID.randomUUID());

                int numberOfSUI = 1 + random.nextInt(3); // 1~3의 랜덤 SUI 개수
                for (int j = 0; j < numberOfSUI; j++) {
                    StudyUnitInstanceV2 sui = new StudyUnitInstanceV2();
                    sui.setId(UUID.randomUUID());
                    int numberOfSAI = 10 + random.nextInt(11); // 10~20의 랜덤 SAI 개수
                    for (int k = 0; k < numberOfSAI; k++) {
                        StudyActivityInstanceV2 sai = StudyActivityInstanceV2.of(random.nextInt(5) + 1);
                        sui.addStudyActivityInstance(sai);
                    }
                    smi.addStudyUnitInstance(sui);
                }
                feedV2.addStudyModuleInstance(smi);
                feedRepositoryV2.save(feedV2);
            }
        }
    }

    @Transactional
    public void createFeed(FeedV2 feed) {
        feedRepositoryV2.save(feed);
    }

    @Transactional(readOnly = true)
    public List<FeedDtoV2> searchFeeds(FeedSearchRequestV2 request) {
        return feedRepositoryV2.searchFeeds(request);
    }

    public FeedDtoV2 getFeedById(Long id) {
        FeedV2 feedV2 = feedRepositoryV2.findById(id)
                .orElseThrow(() -> new RuntimeException("Feed not found with id: " + id));

        return FeedDtoV2.from(
                feedV2.getId(),
                feedV2.getMemberV2().getName(),
                feedV2.getMemberV2().getId(),
                feedV2.getStudyModuleInstanceV2List().stream().map(smi -> new SMIDto(
                        smi.getId(),
                        smi.getCompleteRate(),
                        smi.getStudyUnitInstanceV2List().stream().map(sui -> new SUIDto(
                                sui.getId(),
                                sui.getCompleteRate(),
                                sui.getStudyActivityInstanceV2List().stream().map(sai -> new SAIDto(
                                        sai.getId(),
                                        sai.getAnswer(),
                                        sai.getUserAnswer(),
                                        sai.getIsCorrect()
                                )).collect(Collectors.toList())
                        )).collect(Collectors.toList())
                )).collect(Collectors.toList())
        );
    }
}