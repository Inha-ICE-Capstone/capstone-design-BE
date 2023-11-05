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
public class AgeGroupQuerydslRepositoryImpl implements AgeGroupQuerydslRepository {

}
