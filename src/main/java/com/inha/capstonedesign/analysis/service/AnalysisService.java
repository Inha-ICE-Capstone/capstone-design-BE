package com.inha.capstonedesign.analysis.service;

import com.inha.capstonedesign.analysis.dto.response.CandidateForAnalysisResponseDto;
import com.inha.capstonedesign.analysis.repository.GenderVotingAnalysisRepository;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import com.inha.capstonedesign.voting.exception.VotingException;
import com.inha.capstonedesign.voting.exception.VotingExceptionType;
import com.inha.capstonedesign.voting.repository.CandidateRepository;
import com.inha.capstonedesign.voting.repository.VotingRecordRepository;
import com.inha.capstonedesign.voting.repository.ballot.BallotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisService {

    private final GenderVotingAnalysisRepository genderRepository;
    private final CandidateRepository candidateRepository;
    private final BallotRepository ballotRepository;
    private final VotingRecordRepository votingRecordRepository;

    public List<CandidateForAnalysisResponseDto.BasedGender> getGenderAnalysis(Long ballotId) {

        Ballot ballot = ballotRepository.findByBallotId(ballotId)
                .orElseThrow(() -> new VotingException(VotingExceptionType.BALLOT_NOT_EXISTS));

        Map<Gender, Long> genderTotalCounts = new EnumMap<>(Gender.class);
        for (Gender gender : Gender.values()) {
            long count = votingRecordRepository.countByBallotAndVoterMemberGenderAndVotingRecordStatus(ballot, gender, VotingRecordStatus.COMPLETED);
            genderTotalCounts.put(gender, count);
        }
        List<Candidate> candidates = ballot.getCandidates();

        List<CandidateForAnalysisResponseDto.BasedGender> basedGenders = candidates.stream().map(candidate -> {
            Map<Gender, Long> genderCounts = new EnumMap<>(Gender.class);
            Map<Gender, Double> genderPercentages = new EnumMap<>(Gender.class);

            for (Gender gender : Gender.values()) {
                long count = genderRepository.countByCandidateAndGender(candidate, gender);
                genderCounts.put(gender, count);
                double percentage = genderTotalCounts.get(gender) > 0 ? (double) genderCounts.get(gender) / genderTotalCounts.get(gender) * 100.0 : 0.0;
                genderPercentages.put(gender, percentage);
            }

            return CandidateForAnalysisResponseDto.BasedGender.of(candidate, genderPercentages, genderCounts);
        }).collect(Collectors.toList());

        return basedGenders;
    }
}
