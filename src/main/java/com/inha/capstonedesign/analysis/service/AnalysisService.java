package com.inha.capstonedesign.analysis.service;

import com.inha.capstonedesign.analysis.dto.response.CandidateForAnalysisResponseDto;
import com.inha.capstonedesign.analysis.entity.AgeGroup;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import com.inha.capstonedesign.voting.exception.VotingException;
import com.inha.capstonedesign.voting.exception.VotingExceptionType;
import com.inha.capstonedesign.voting.repository.CandidateRepository;
import com.inha.capstonedesign.voting.repository.votingrecord.VotingRecordRepository;
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
                genderCounts.put(gender, count);
                double percentage = genderTotalCounts.get(gender) > 0 ? (double) genderCounts.get(gender) / genderTotalCounts.get(gender) * 100.0 : 0.0;
                genderPercentages.put(gender, percentage);
            }

            return CandidateForAnalysisResponseDto.BasedGender.of(candidate, genderPercentages, genderCounts);
        }).collect(Collectors.toList());

        return basedGenders;
    }

    public List<CandidateForAnalysisResponseDto.BasedRegion> getRegionAnalysis(Long ballotId) {

        Ballot ballot = ballotRepository.findByBallotId(ballotId)
                .orElseThrow(() -> new VotingException(VotingExceptionType.BALLOT_NOT_EXISTS));

        Map<Region, Long> regionTotalCounts = new EnumMap<>(Region.class);
        for (Region region : Region.values()) {
            long count = votingRecordRepository.countByBallotAndVoterMemberRegionAndVotingRecordStatus(ballot, region, VotingRecordStatus.COMPLETED);
            regionTotalCounts.put(region, count);
        }
        List<Candidate> candidates = ballot.getCandidates();

        List<CandidateForAnalysisResponseDto.BasedRegion> basedRegions = candidates.stream().map(candidate -> {
            Map<Region, Long> regionCounts = new EnumMap<>(Region.class);
            Map<Region, Double> regionPercentages = new EnumMap<>(Region.class);

            for (Region region : Region.values()) {
                regionCounts.put(region, count);
                double percentage = regionTotalCounts.get(region) > 0 ? (double) regionCounts.get(region) / regionTotalCounts.get(region) * 100.0 : 0.0;
                regionPercentages.put(region, percentage);
            }

            return CandidateForAnalysisResponseDto.BasedRegion.of(candidate, regionPercentages, regionCounts);
        }).collect(Collectors.toList());

        return basedRegions;
    }

    public List<CandidateForAnalysisResponseDto.BasedAgeGroup> getAgeGroupAnalysis(Long ballotId) {

        Ballot ballot = ballotRepository.findByBallotId(ballotId)
                .orElseThrow(() -> new VotingException(VotingExceptionType.BALLOT_NOT_EXISTS));

        Map<AgeGroup, Long> ageGroupTotalCounts = new EnumMap<>(AgeGroup.class);
        for (AgeGroup ageGroup : AgeGroup.values()) {
            long count = votingRecordRepository.countByBallotAndAgeGroup(ballot, ageGroup, VotingRecordStatus.COMPLETED);
            ageGroupTotalCounts.put(ageGroup, count);
        }
        List<Candidate> candidates = ballot.getCandidates();

        List<CandidateForAnalysisResponseDto.BasedAgeGroup> basedAgeGroups = candidates.stream().map(candidate -> {
            Map<AgeGroup, Long> ageGroupCounts = new EnumMap<>(AgeGroup.class);
            Map<AgeGroup, Double> ageGroupPercentages = new EnumMap<>(AgeGroup.class);

            for (AgeGroup ageGroup : AgeGroup.values()) {
                ageGroupCounts.put(ageGroup, count);
                double percentage = ageGroupTotalCounts.get(ageGroup) > 0 ? (double) ageGroupCounts.get(ageGroup) / ageGroupTotalCounts.get(ageGroup) * 100.0 : 0.0;
                ageGroupPercentages.put(ageGroup, percentage);
            }

            return CandidateForAnalysisResponseDto.BasedAgeGroup.of(candidate, ageGroupPercentages, ageGroupCounts);
        }).collect(Collectors.toList());

        return basedAgeGroups;
    }
}
