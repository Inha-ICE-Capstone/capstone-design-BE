package com.inha.capstonedesign.voting.repository.votingrecord;

import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Member;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.VotingRecord;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotingRecordRepository extends JpaRepository<VotingRecord, Long>, VotingRecordQuerydslRepository {

    Optional<VotingRecord> findByVoterAndBallot(Member member, Ballot ballot);
    Long countByBallotAndVoterMemberGenderAndVotingRecordStatus(Ballot ballot, Gender gender, VotingRecordStatus votingRecordStatus);
    Long countByBallotAndVoterMemberRegionAndVotingRecordStatus(Ballot ballot, Region region, VotingRecordStatus votingRecordStatus);

    @EntityGraph(attributePaths = {"ballot", "ballot.ballotImage"})
    Page<VotingRecord> findByVoterOrderByVotingRecordIdDesc(Member member, Pageable pageable);
}
