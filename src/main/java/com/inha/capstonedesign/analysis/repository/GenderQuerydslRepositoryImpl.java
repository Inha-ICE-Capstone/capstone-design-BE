package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.inha.capstonedesign.analysis.entity.gender.QGenderVotingAnalysis.genderVotingAnalysis;

@RequiredArgsConstructor
@Repository
public class GenderQuerydslRepositoryImpl implements GenderQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long countByCandidateAndGender(Candidate candidate, Gender gender) {
        return queryFactory.select(Wildcard.count)
                .from(genderVotingAnalysis)
                .where(genderVotingAnalysis.candidate.eq(candidate), genderVotingAnalysis.gender.eq(gender))
                .fetchOne();
    }
}
