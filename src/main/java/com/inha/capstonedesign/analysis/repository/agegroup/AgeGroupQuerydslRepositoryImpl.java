package com.inha.capstonedesign.analysis.repository.agegroup;

import com.inha.capstonedesign.analysis.entity.age.AgeGroup;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.inha.capstonedesign.analysis.entity.age.QAgeGroupVotingAnalysis.ageGroupVotingAnalysis;

@RequiredArgsConstructor
@Repository
public class AgeGroupQuerydslRepositoryImpl implements AgeGroupQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long countByCandidateAndAgeGroup(Candidate candidate, AgeGroup ageGroup) {
        return queryFactory.select(Wildcard.count)
                .from(ageGroupVotingAnalysis)
                .where(ageGroupVotingAnalysis.ageGroup.eq(ageGroup), ageGroupVotingAnalysis.candidate.eq(candidate))
                .fetchOne();
    }
}
