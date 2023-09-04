package com.inha.capstonedesign.voting.repository;

import com.inha.capstonedesign.voting.entity.Ballot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BallotRepository extends JpaRepository<Ballot, Long> {
}
