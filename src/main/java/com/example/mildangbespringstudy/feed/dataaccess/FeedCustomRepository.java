package com.example.mildangbespringstudy.feed.dataaccess;


import com.example.mildangbespringstudy.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.feed.domain.Feed;
import com.example.mildangbespringstudy.feed.domain.QFeed;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSummaryResponse;
import com.example.mildangbespringstudy.samplecrud.domain.QTeam;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FeedCustomRepository {

    private final JPAQueryFactory queryFactory;

    public FeedCustomRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<FeedDto> searchFeeds(FeedSearchRequest request) {
        /**
         * TODO: FeedSearchRequest에 담긴 정보를 이용하여 Feed를 검색하는 쿼리를 작성하세요.
         * queryDSL을 사용하여 작성합니다.
         * request에 당긴 정보로 검색 조건을 설정합니다.
         * memberName 으로 검색하는 조건을 추가합니다.
         * page, size 정보를 이용하여 페이징 처리를 합니다.
         * 피드, SU, SUI, SAI를 모두 검색합니다.
         * 검색된 Feed를 FeedDto로 변환하여 반환합니다.
         */
        return queryFactory
            .select(Projections.constructor(FeedDto.class, QFeed.feed.id, QFeed.feed.member, QFeed.feed.studyModuleInstanceList))
            .from(QFeed.feed)
            .where(memberNameLike(request.getMemberName()))
            .orderBy(QFeed.feed.id.desc())
            .offset(request.getOffset())
            .limit(request.getSize())
            .fetch();
    }

    public Optional<FeedDto> findById(Long id) {
        /**
         * TODO: id를 이용하여 Feed를 검색하는 쿼리를 작성하세요.
         * queryDSL을 사용하여 작성합니다.
         * 검색된 Feed를 Optional로 반환합니다.
         * 존재하지 않는 경우 Optional.empty()를 반환합니다.
         * 피드, SU, SUI, SAI를 모두 검색합니다.
         */
        List<FeedDto> feeds = queryFactory
            .select(Projections.constructor(FeedDto.class, QFeed.feed.id, QFeed.feed.member, QFeed.feed.studyModuleInstanceList))
            .from(QFeed.feed)
            .where(QFeed.feed.id.eq(id))
            .offset(0)
            .limit(1)
            .fetch();
        if(feeds.isEmpty()) return Optional.empty();
        return feeds.stream().findFirst();
    }

    private BooleanExpression memberNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return QFeed.feed.member.name.startsWith(name);
    }
}