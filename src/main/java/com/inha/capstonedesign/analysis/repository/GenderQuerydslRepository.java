package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.voting.entity.Candidate;

public interface GenderQuerydslRepository {

    Long countByCandidateAndGender(Candidate candidate, Gender gender);
}
