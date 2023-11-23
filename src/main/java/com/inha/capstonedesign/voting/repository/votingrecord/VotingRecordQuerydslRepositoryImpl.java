package com.inha.capstonedesign.voting.repository.votingrecord;

import com.inha.capstonedesign.analysis.entity.AgeGroup;
import com.inha.capstonedesign.member.entity.QMember;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.inha.capstonedesign.member.entity.QMember.member;
import static com.inha.capstonedesign.voting.entity.QVotingRecord.votingRecord;

@RequiredArgsConstructor
@Repository
public class VotingRecordQuerydslRepositoryImpl implements VotingRecordQuerydslRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Long countByBallotAndAgeGroup(Ballot ballot, AgeGroup ageGroup, VotingRecordStatus votingRecordStatus) {

        return queryFactory.select(Wildcard.count)
                .from(votingRecord)
                .join(votingRecord.voter, member)
                .where(findAgeGroup(votingRecord.voter, ageGroup), votingRecord.ballot.eq(ballot),
                        votingRecord.votingRecordStatus.eq(votingRecordStatus))
                .fetchOne();
    }

    private BooleanExpression findAgeGroup(QMember member, AgeGroup ageGroup) {
        if (ageGroup.equals(AgeGroup.TEENS_OR_LESS)) {
            return member.memberAge.between(0, 19);
        } else if (ageGroup.equals(AgeGroup.TWENTIES)) {
            return member.memberAge.between(20, 29);
        } else if (ageGroup.equals(AgeGroup.THIRTIES)) {
            return member.memberAge.between(30, 39);
        } else if (ageGroup.equals(AgeGroup.FORTIES)) {
            return member.memberAge.between(40, 49);
        } else if (ageGroup.equals(AgeGroup.FIFTIES)) {
            return member.memberAge.between(50, 59);
        } else {
            return member.memberAge.between(60, 200);
        }
    }
}
