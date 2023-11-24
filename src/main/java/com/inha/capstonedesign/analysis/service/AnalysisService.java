package com.inha.capstonedesign.analysis.service;

import com.inha.capstonedesign.analysis.dto.response.CandidateForAnalysisResponseDto;
import com.inha.capstonedesign.analysis.entity.AgeGroup;
import com.inha.capstonedesign.analysis.repository.VotingAnalysisRepository;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import com.inha.capstonedesign.voting.exception.VotingException;
import com.inha.capstonedesign.voting.exception.VotingExceptionType;
import com.inha.capstonedesign.voting.repository.ballot.BallotRepository;
import com.inha.capstonedesign.voting.repository.votingrecord.VotingRecordRepository;
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

    private final VotingAnalysisRepository votingAnalysisRepository;
    private final BallotRepository ballotRepository;
    private final VotingRecordRepository votingRecordRepository;

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
                long count = votingAnalysisRepository.countByCandidateAndRegion(candidate, region);
                regionCounts.put(region, count);
                double percentage = regionTotalCounts.get(region) > 0 ? (double) regionCounts.get(region) / regionTotalCounts.get(region) * 100.0 : 0.0;
                regionPercentages.put(region, percentage);
            }

            return CandidateForAnalysisResponseDto.BasedRegion.of(candidate, regionPercentages, regionCounts);
        }).collect(Collectors.toList());

        return basedRegions;
    }

    public List<CandidateForAnalysisResponseDto.BasedAgeGroupAndGender> getAgeGroupAndGenderAnalysis(Long ballotId) {

        Ballot ballot = ballotRepository.findByBallotId(ballotId)
                .orElseThrow(() -> new VotingException(VotingExceptionType.BALLOT_NOT_EXISTS));

        Map<AgeGroup, Map<Gender, Long>> ageGroupTotalCounts = new EnumMap<>(AgeGroup.class);
        for (AgeGroup ageGroup : AgeGroup.values()) {
            EnumMap<Gender, Long> genderCounts = new EnumMap<>(Gender.class);
            for (Gender gender : Gender.values()) {
                long count = votingRecordRepository.countByBallotAndAgeGroupAndGender(ballot, ageGroup, gender, VotingRecordStatus.COMPLETED);
                genderCounts.put(gender, count);
                ageGroupTotalCounts.put(ageGroup, genderCounts);
            }
        }
        List<Candidate> candidates = ballot.getCandidates();

        List<CandidateForAnalysisResponseDto.BasedAgeGroupAndGender> basedAgeGroupAndGenders = candidates.stream().map(candidate -> {
            Map<AgeGroup, Map<Gender, Long>> ageGroupCounts = new EnumMap<>(AgeGroup.class);
            Map<AgeGroup, Map<Gender, Double>> ageGroupPercentages = new EnumMap<>(AgeGroup.class);

            for (AgeGroup ageGroup : AgeGroup.values()) {
                EnumMap<Gender, Long> genderCounts = new EnumMap<>(Gender.class);
                EnumMap<Gender, Double> genderPercentages = new EnumMap<>(Gender.class);
                for (Gender gender : Gender.values()) {
                    long count = votingAnalysisRepository.countByCandidateAndAgeGroupAndGender(candidate, ageGroup, gender);

                    double percentage = ageGroupTotalCounts.get(ageGroup).get(gender) > 0 ? (double) count / ageGroupTotalCounts.get(ageGroup).get(gender) * 100.0 : 0.0;
                    genderCounts.put(gender, count);
                    genderPercentages.put(gender, percentage);

                    ageGroupCounts.put(ageGroup, genderCounts);
                    ageGroupPercentages.put(ageGroup, genderPercentages);
                }
            }
            return CandidateForAnalysisResponseDto.BasedAgeGroupAndGender.of(candidate, ageGroupPercentages, ageGroupCounts);
        }).collect(Collectors.toList());

        return basedAgeGroupAndGenders;
    }
}
