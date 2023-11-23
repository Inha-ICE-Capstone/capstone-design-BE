package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.VotingAnalysis;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingAnalysisRepository extends JpaRepository<VotingAnalysis, Long>, VotingAnalysisQuerydslRepository {
    Long countByCandidateAndGender(Candidate candidate, Gender gender);

    Long countByCandidateAndRegion(Candidate candidate, Region region);
}
