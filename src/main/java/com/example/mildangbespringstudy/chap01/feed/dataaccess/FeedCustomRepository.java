package com.example.mildangbespringstudy.chap01.feed.dataaccess;

import com.example.mildangbespringstudy.chap01.domain.*;
import com.example.mildangbespringstudy.chap01.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.chap01.feed.domain.Feed;
import com.example.mildangbespringstudy.chap01.feed.domain.QFeed;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FeedCustomRepository {

    private final JPAQueryFactory queryFactory;

    public FeedCustomRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<FeedDto> searchFeeds(FeedSearchRequest request) {
        QFeed feed = QFeed.feed;

        List<Feed> feedList = queryFactory
                .selectFrom(feed)
                .where(memberNameLike(request.getMemberName()))
                .orderBy(feed.id.desc())
                .offset(request.getOffset())
                .limit(request.getSize())
                .fetch();

        return feedList.stream().map(f -> {
            List<SMIDto> smiList = f.getStudyModuleInstanceList().stream().map(s -> {
                List<SUIDto> suiList = s.getStudyUnitInstanceList().stream().map(u -> {
                    List<SAIDto> saiList = u.getStudyActivityInstanceList().stream().map(a ->
                            new SAIDto(a.getId(), a.getAnswer(), a.getUserAnswer(), a.getIsCorrect())
                    ).collect(Collectors.toList());
                    return new SUIDto(u.getId(), u.getCompleteRate(), saiList);
                }).collect(Collectors.toList());
                return new SMIDto(s.getId(), s.getCompleteRate(), suiList);
            }).collect(Collectors.toList());
            return new FeedDto(f.getId(), smiList);
        }).collect(Collectors.toList());
    }

    public Optional<Feed> findById(Long id) {
        QFeed feed = QFeed.feed;

        Feed foundFeed = queryFactory.selectFrom(feed)
                .where(feed.id.eq(id))
                .fetchOne();

        // Null 체크를 Optional로 처리
        if (foundFeed == null) {
            return Optional.empty();
        }

        // 모든 수정을 수집한 후에 컬렉션에 추가
        List<StudyModuleInstance> collectedSMIs = foundFeed.getStudyModuleInstanceList().stream().map(smi -> {
            List<StudyUnitInstance> collectedSUIs = smi.getStudyUnitInstanceList().stream().map(sui -> {
                List<StudyActivityInstance> collectedSAIs = sui.getStudyActivityInstanceList().stream()
                        .toList();
                sui.getStudyActivityInstanceList().clear();
                sui.getStudyActivityInstanceList().addAll(collectedSAIs);
                return sui;
            }).toList();
            smi.getStudyUnitInstanceList().clear();
            smi.getStudyUnitInstanceList().addAll(collectedSUIs);
            return smi;
        }).toList();

        foundFeed.getStudyModuleInstanceList().clear();
        foundFeed.getStudyModuleInstanceList().addAll(collectedSMIs);

        return Optional.of(foundFeed);
    }

    private BooleanExpression memberNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return QFeed.feed.member.name.startsWith(name);
    }
}