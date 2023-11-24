package com.inha.capstonedesign.voting.repository.votingrecord;

import com.inha.capstonedesign.analysis.entity.AgeGroup;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;

public interface VotingRecordQuerydslRepository {

    Long countByBallotAndAgeGroupAndGender(Ballot ballot, AgeGroup ageGroup, Gender gender, VotingRecordStatus votingRecordStatus);
}
