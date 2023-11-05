package com.inha.capstonedesign.analysis.repository;

import com.inha.capstonedesign.analysis.entity.age.AgeGroupVotingAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AgeGroupVotingAnalysisRepository extends JpaRepository<AgeGroupVotingAnalysis, Long>, AgeGroupQuerydslRepository {

}
