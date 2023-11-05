package com.inha.capstonedesign.voting.repository;

import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Member;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.VotingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotingRecordRepository extends JpaRepository<VotingRecord, Long> {

    Optional<VotingRecord> findByVoterAndBallot(Member member, Ballot ballot);
    Long countByBallotAndVoterMemberGender(Ballot ballot, Gender gender);

    @EntityGraph(attributePaths = {"ballot", "ballot.ballotImage"})
    Page<VotingRecord> findByVoterOrderByVotingRecordIdDesc(Member member, Pageable pageable);
}
