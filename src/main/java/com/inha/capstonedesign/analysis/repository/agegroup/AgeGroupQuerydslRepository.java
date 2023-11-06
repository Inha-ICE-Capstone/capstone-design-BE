package com.inha.capstonedesign.analysis.repository.agegroup;

import com.inha.capstonedesign.analysis.entity.age.AgeGroup;
import com.inha.capstonedesign.voting.entity.Candidate;

public interface AgeGroupQuerydslRepository {
    Long countByCandidateAndAgeGroup(Candidate candidate, AgeGroup ageGroup);
}
