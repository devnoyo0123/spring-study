package com.example.mildangbespringstudy.chap01.feed.application;

import com.example.mildangbespringstudy.chap01.domain.StudyActivityInstance;
import com.example.mildangbespringstudy.chap01.domain.StudyModuleInstance;
import com.example.mildangbespringstudy.chap01.domain.StudyUnitInstance;
import com.example.mildangbespringstudy.chap01.feed.application.dto.CreateFeedRequest;
import com.example.mildangbespringstudy.chap01.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.chap01.feed.dataaccess.*;
import com.example.mildangbespringstudy.chap01.feed.domain.Feed;
import com.example.mildangbespringstudy.chap01.member.dataaccess.MemberJpaRepository;
import com.example.mildangbespringstudy.chap01.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final MemberJpaRepository memberRepository;

    @Transactional
    public void createFeeds(CreateFeedRequest request) {
        Random random = new Random();

        for (long memberId = 1; memberId <= request.maxMemberId(); memberId++) {
            long finalMemberId = memberId;
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("Member not found with id: " + finalMemberId));

            for (int feedCount = 0; feedCount < request.numberOfFeed(); feedCount++) {
                Feed feed = new Feed();
                feed.setMember(member);
                StudyModuleInstance smi = new StudyModuleInstance();
                smi.setId(UUID.randomUUID());

                int numberOfSUI = 1 + random.nextInt(3); // 1~3의 랜덤 SUI 개수
                for (int j = 0; j < numberOfSUI; j++) {
                    StudyUnitInstance sui = new StudyUnitInstance();
                    sui.setId(UUID.randomUUID());
                    int numberOfSAI = 10 + random.nextInt(11); // 10~20의 랜덤 SAI 개수
                    for (int k = 0; k < numberOfSAI; k++) {
                        StudyActivityInstance sai = StudyActivityInstance.of(random.nextInt(5) + 1);
                        sui.addStudyActivityInstance(sai);
                    }
                    smi.addStudyUnitInstance(sui);
                }
                feed.addStudyModuleInstance(smi);
                feedRepository.save(feed);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<FeedDto> searchFeeds(FeedSearchRequest request) {
        return feedRepository.searchFeeds(request);
    }

    public FeedDto getFeedById(Long id) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feed not found with id: " + id));

        return new FeedDto(
                feed.getId(),
                feed.getStudyModuleInstanceList().stream().map(smi -> new SMIDto(
                        smi.getId(),
                        smi.getCompleteRate(),
                        smi.getStudyUnitInstanceList().stream().map(sui -> new SUIDto(
                                sui.getId(),
                                sui.getCompleteRate(),
                                sui.getStudyActivityInstanceList().stream().map(sai -> new SAIDto(
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