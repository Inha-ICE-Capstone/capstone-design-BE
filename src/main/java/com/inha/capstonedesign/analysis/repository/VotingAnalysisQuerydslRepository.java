package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.AgeGroup;
import com.inha.capstonedesign.voting.entity.Candidate;

public interface VotingAnalysisQuerydslRepository {
    Long countByCandidateAndAgeGroup(Candidate candidate, AgeGroup ageGroup);
}
