package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.AgeGroup;
import com.inha.capstonedesign.member.entity.Gender;
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
    public Long countByCandidateAndAgeGroupAndGender(Candidate candidate, AgeGroup ageGroup, Gender gender) {
        return queryFactory.select(Wildcard.count)
                .from(votingAnalysis)
                .where(votingAnalysis.ageGroup.eq(ageGroup), votingAnalysis.gender.eq(gender), votingAnalysis.candidate.eq(candidate))
                .fetchOne();
    }
}
