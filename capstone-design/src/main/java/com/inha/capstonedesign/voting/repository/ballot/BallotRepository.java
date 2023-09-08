package com.inha.capstonedesign.voting.repository.ballot;

import com.inha.capstonedesign.voting.entity.Ballot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BallotRepository extends JpaRepository<Ballot, Long>, BallotQuerydslRepository {

    Page<Ballot> findAllByBallotStatusOrderByBallotEndDateTime(Pageable pageable, String status);
}
