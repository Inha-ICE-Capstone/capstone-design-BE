package com.inha.capstonedesign.voting.repository.ballot;

import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.BallotStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.inha.capstonedesign.image.entity.QBallotImage.ballotImage;
import static com.inha.capstonedesign.voting.entity.QBallot.ballot;

@RequiredArgsConstructor
@Repository
public class BallotQuerydslRepositoryImpl implements BallotQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Ballot> findAllByBallotStatusOrderByBallotEndDateTime(BallotStatus ballotStatus) {
        List<Ballot> ballots = queryFactory.selectFrom(ballot)
                .leftJoin(ballot.ballotImage, ballotImage)
                .fetchJoin()
                .where(ballot.ballotStatus.eq(ballotStatus))
                .orderBy(ballot.ballotEndDateTime.asc())
                .fetch();
        return ballots;
    }
}
