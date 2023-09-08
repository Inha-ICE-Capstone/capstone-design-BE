package com.inha.capstonedesign.voting.repository.ballot;

import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.BallotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BallotRepository extends JpaRepository<Ballot, Long>, BallotQuerydslRepository {

    List<Ballot> findAllByBallotStatusOrderByBallotEndDateTime(BallotStatus ballotStatus);
}
