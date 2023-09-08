package com.inha.capstonedesign.voting.repository.ballot;

import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.BallotStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallotQuerydslRepository {

    List<Ballot> findAllByBallotStatusOrderByBallotEndDateTime(BallotStatus ballotStatus);
}
