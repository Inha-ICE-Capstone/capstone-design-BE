package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.gender.GenderVotingAnalysis;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.voting.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderVotingAnalysisRepository extends JpaRepository<GenderVotingAnalysis, Long> {
    Long countByCandidateAndGender(Candidate candidate, Gender gender);
}
