package com.inha.capstonedesign.analysis.service;

import com.inha.capstonedesign.analysis.dto.response.CandidateForAnalysisResponseDto;
import com.inha.capstonedesign.analysis.repository.GenderVotingAnalysisRepository;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.inha.capstonedesign.voting.exception.VotingException;
import com.inha.capstonedesign.voting.exception.VotingExceptionType;
import com.inha.capstonedesign.voting.repository.CandidateRepository;
import com.inha.capstonedesign.voting.repository.VotingRecordRepository;
import com.inha.capstonedesign.voting.repository.ballot.BallotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        Long maleTotalCount = votingRecordRepository.countByBallotAndVoterMemberGender(ballot, Gender.MALE);
        Long femaleTotalCount = votingRecordRepository.countByBallotAndVoterMemberGender(ballot, Gender.FEMALE);
        List<Candidate> candidates = ballot.getCandidates();

        List<CandidateForAnalysisResponseDto.BasedGender> basedGenders = candidates.stream().map(candidate -> {
            Long maleCount = genderRepository.countByCandidateAndGender(candidate, Gender.MALE);
            Long femaleCount = genderRepository.countByCandidateAndGender(candidate, Gender.FEMALE);
            double maleVotePercentage = maleTotalCount > 0 ? (double) maleCount / maleTotalCount * 100.0 : 0.0;
            double femaleVotePercentage = femaleTotalCount > 0 ? (double) femaleCount / femaleTotalCount * 100.0 : 0.0;

            return CandidateForAnalysisResponseDto.BasedGender.of(candidate, maleCount, femaleCount, maleVotePercentage, femaleVotePercentage);
        }).collect(Collectors.toList());

        return basedGenders;
    }

}
