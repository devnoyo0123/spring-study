package com.example.mildangbespringstudy.feed.application;


import com.example.mildangbespringstudy.domain.StudyActivityInstance;
import com.example.mildangbespringstudy.domain.StudyModuleInstance;
import com.example.mildangbespringstudy.domain.StudyUnitInstance;
import com.example.mildangbespringstudy.feed.application.dto.CreateFeedRequest;
import com.example.mildangbespringstudy.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.feed.dataaccess.FeedDto;
import com.example.mildangbespringstudy.feed.dataaccess.FeedRepository;
import com.example.mildangbespringstudy.feed.domain.Feed;
import com.example.mildangbespringstudy.member.dataaccess.MemberJpaRepository;
import com.example.mildangbespringstudy.member.domain.Member;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

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

    public List<FeedDto> searchFeeds(FeedSearchRequest request) {
        /**
         * TODO: FeedRepository를 이용하여 피드, SMI, SUI, SAI를 검색하는 코드를 작성하세요.
         * FeedSearchRequest 객체를 이용하여 검색 조건을 받아오세요.
         */
        return feedRepository.searchFeeds(request);
    }

    public FeedDto getFeedById(Long id) {
        /**
         * TODO: FeedRepository를 이용하여 id에 해당하는 피드, SMI, SUI, SAI를 조회하는 코드를 작성하세요.
         */
        Feed feed = feedRepository.findById(id).orElseThrow(() -> new RuntimeException("Feed not found with id: " + id));

        return FeedDto.of(feed);
    }
}