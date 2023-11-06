package com.inha.capstonedesign.voting.repository.votingrecord;

import com.inha.capstonedesign.analysis.entity.age.AgeGroup;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;

public interface VotingRecordQuerydslRepository {

    Long countByBallotAndAgeGroup(Ballot ballot, AgeGroup ageGroup, VotingRecordStatus votingRecordStatus);
}
