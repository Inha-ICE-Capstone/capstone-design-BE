package com.inha.capstonedesign.analysis.repository.agegroup;

import com.inha.capstonedesign.analysis.entity.age.AgeGroupVotingAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeGroupVotingAnalysisRepository extends JpaRepository<AgeGroupVotingAnalysis, Long>, AgeGroupQuerydslRepository{
}
