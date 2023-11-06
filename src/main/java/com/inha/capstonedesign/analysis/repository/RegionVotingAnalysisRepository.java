package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.region.RegionVotingAnalysis;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionVotingAnalysisRepository extends JpaRepository<RegionVotingAnalysis, Long> {
    Long countByCandidateAndRegion(Candidate candidate, Region region);
}
