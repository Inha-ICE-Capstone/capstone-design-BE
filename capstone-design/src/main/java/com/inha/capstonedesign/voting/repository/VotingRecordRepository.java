package com.inha.capstonedesign.voting.repository;

import com.inha.capstonedesign.voting.entity.VotingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingRecordRepository extends JpaRepository<VotingRecord, Long> {
}
