package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.AgeGroup;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.inha.capstonedesign.analysis.entity.QVotingAnalysis.votingAnalysis;

@RequiredArgsConstructor
@Repository
public class VotingAnalysisQuerydslRepositoryImpl implements VotingAnalysisQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long countByCandidateAndAgeGroup(Candidate candidate, AgeGroup ageGroup) {
        return queryFactory.select(Wildcard.count)
                .from(votingAnalysis)
                .where(votingAnalysis.ageGroup.eq(ageGroup), votingAnalysis.candidate.eq(candidate))
                .fetchOne();
    }
}
