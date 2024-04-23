package com.example.mildangbespringstudy.feed.dataaccess;


import com.example.mildangbespringstudy.domain.QStudyActivityInstance;
import com.example.mildangbespringstudy.domain.QStudyModuleInstance;
import com.example.mildangbespringstudy.domain.QStudyUnitInstance;
import com.example.mildangbespringstudy.feed.application.dto.FeedSearchRequest;
import com.example.mildangbespringstudy.feed.domain.Feed;
import com.example.mildangbespringstudy.feed.domain.QFeed;
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
        return null;
    }

    public Optional<Feed> findById(Long id) {
        Feed feed = this.queryFactory.selectFrom(QFeed.feed)
            .join(QStudyModuleInstance.studyModuleInstance)
            .on(QFeed.feed.id.eq(QStudyModuleInstance.studyModuleInstance.feed.id))
            .join(QStudyUnitInstance.studyUnitInstance)
            .on(QStudyModuleInstance.studyModuleInstance.id.eq(QStudyUnitInstance.studyUnitInstance.studyModuleInstance.id))
            .where(QFeed.feed.id.eq(id)).fetchOne();

        System.out.println("feed -->" + feed);

        return Optional.ofNullable(feed);


        /**
         * TODO: id를 이용하여 Feed를 검색하는 쿼리를 작성하세요.
         * queryDSL을 사용하여 작성합니다.
         * 검색된 Feed를 Optional로 반환합니다.
         * 존재하지 않는 경우 Optional.empty()를 반환합니다.
         * 피드, SU, SUI, SAI를 모두 검색합니다.
         */
    }

    private BooleanExpression memberNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return QFeed.feed.member.name.startsWith(name);
    }
}