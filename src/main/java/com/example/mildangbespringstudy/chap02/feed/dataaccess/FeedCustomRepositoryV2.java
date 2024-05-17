package com.example.mildangbespringstudy.chap02.feed.dataaccess;

import com.example.mildangbespringstudy.chap02.domain.domain.StudyActivityInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyModuleInstanceV2;
import com.example.mildangbespringstudy.chap02.domain.domain.StudyUnitInstanceV2;
import com.example.mildangbespringstudy.chap02.feed.application.dto.FeedSearchRequestV2;
import com.example.mildangbespringstudy.chap02.feed.domain.FeedV2;
import com.example.mildangbespringstudy.chap02.feed.domain.QFeedV2;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FeedCustomRepositoryV2 {

    private final JPAQueryFactory queryFactory;

    public FeedCustomRepositoryV2(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<FeedDtoV2> searchFeeds(FeedSearchRequestV2 request) {
        QFeedV2 feed = QFeedV2.feedV2;

        List<FeedV2> feedV2List = queryFactory
                .selectFrom(feed)
                .where(memberNameLike(request.getMemberName()))
                .orderBy(feed.id.desc())
                .offset(request.getOffset())
                .limit(request.getSize())
                .fetch();

        return feedV2List.stream().map(f -> {
            List<SMIDto> smiList = f.getStudyModuleInstanceV2List().stream().map(s -> {
                List<SUIDto> suiList = s.getStudyUnitInstanceV2List().stream().map(u -> {
                    List<SAIDto> saiList = u.getStudyActivityInstanceV2List().stream().map(a ->
                            new SAIDto(a.getId(), a.getAnswer(), a.getUserAnswer(), a.getIsCorrect())
                    ).collect(Collectors.toList());
                    return new SUIDto(u.getId(), u.getCompleteRate(), saiList);
                }).collect(Collectors.toList());
                return new SMIDto(s.getId(), s.getCompleteRate(), suiList);
            }).collect(Collectors.toList());
            return new FeedDtoV2(f.getId(), f.getMemberV2().getName(), f.getMemberV2().getId(), smiList);
        }).collect(Collectors.toList());
    }

    public Optional<FeedV2> findById(Long id) {
        QFeedV2 feed = QFeedV2.feedV2;

        FeedV2 foundFeedV2 = queryFactory.selectFrom(feed)
                .where(feed.id.eq(id))
                .fetchOne();

        // Null 체크를 Optional로 처리
        if (foundFeedV2 == null) {
            return Optional.empty();
        }

        // 모든 수정을 수집한 후에 컬렉션에 추가
        List<StudyModuleInstanceV2> collectedSMIs = foundFeedV2.getStudyModuleInstanceV2List().stream().map(smi -> {
            List<StudyUnitInstanceV2> collectedSUIs = smi.getStudyUnitInstanceV2List().stream().map(sui -> {
                List<StudyActivityInstanceV2> collectedSAIs = sui.getStudyActivityInstanceV2List().stream()
                        .toList();
                sui.getStudyActivityInstanceV2List().addAll(collectedSAIs);
                return sui;
            }).toList();
            smi.getStudyUnitInstanceV2List().addAll(collectedSUIs);
            return smi;
        }).toList();

        foundFeedV2.getStudyModuleInstanceV2List().addAll(collectedSMIs);

        return Optional.of(foundFeedV2);
    }

    private BooleanExpression memberNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return QFeedV2.feedV2.memberV2.name.startsWith(name);
    }
}