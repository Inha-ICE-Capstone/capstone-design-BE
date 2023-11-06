package com.inha.capstonedesign.voting.repository.votingrecord;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VotingRecordQuerydslRepositoryImpl {
    private final JPAQueryFactory queryFactory;


}
