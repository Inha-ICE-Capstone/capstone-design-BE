package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.gender.GenderVotingAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderVotingAnalysisRepository extends JpaRepository<GenderVotingAnalysis, Long> {
}
