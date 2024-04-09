package com.example.mildangbespringstudy.samplecrud.dataaccess;

import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSearchRequest;
import com.example.mildangbespringstudy.samplecrud.application.dto.TeamSummaryResponse;
import com.example.mildangbespringstudy.samplecrud.domain.QTeam;
import com.example.mildangbespringstudy.samplecrud.domain.Team;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamCustomRepository {

    private final JPAQueryFactory queryFactory;

    public TeamCustomRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<TeamSummaryResponse> searchTeam(TeamSearchRequest request) {
        return queryFactory
                .select(Projections.constructor(TeamSummaryResponse.class,
                        QTeam.team.id,
                        QTeam.team.name))
                .from(QTeam.team)
                .where(nameLike(request.getName()))
                .orderBy(QTeam.team.id.desc())
                .offset(request.getOffset())
                .limit(request.getSize())
                .fetch();
    }

    private BooleanExpression nameLike(String name) {
        if (name == null || name.isEmpty()) {
            // 조건 없이 참인 BooleanExpression 반환
            return null; // 또는 Expressions.asBoolean(true).isTrue();
        }
        return QTeam.team.name.startsWith(name);
    }
}
