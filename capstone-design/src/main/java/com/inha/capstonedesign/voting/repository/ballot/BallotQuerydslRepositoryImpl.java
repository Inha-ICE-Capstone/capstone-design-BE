package com.inha.capstonedesign.voting.repository.ballot;

import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.BallotStatus;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.inha.capstonedesign.image.entity.QBallotImage.ballotImage;
import static com.inha.capstonedesign.voting.entity.QBallot.ballot;

@RequiredArgsConstructor
@Repository
public class BallotQuerydslRepositoryImpl implements BallotQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Ballot> findAllByBallotStatusOrderByBallotEndDateTime(Pageable pageable, String status) {

        BallotStatus ballotStatus = getBallotStatusFromString(status);

        List<Ballot> content = queryFactory.selectFrom(ballot)
                .leftJoin(ballot.ballotImage, ballotImage)
                .fetchJoin()
                .where(ballot.ballotStatus.eq(ballotStatus))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(ballot.ballotEndDateTime.asc())
                .fetch();

        long total = queryFactory.select(Wildcard.count)
                .from(ballot)
                .where(ballot.ballotStatus.eq(ballotStatus))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }

    private static BallotStatus getBallotStatusFromString(String status) {
        if (status == null) {
            return BallotStatus.IN_PROGRESS;
        }
        for (BallotStatus ballotStatus : BallotStatus.values()) {
            if (ballotStatus.getKorean().equals(status)) {
                return ballotStatus;
            }
        }
        return BallotStatus.IN_PROGRESS;
    }
}
