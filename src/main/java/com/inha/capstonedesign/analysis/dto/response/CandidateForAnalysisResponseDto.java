package com.inha.capstonedesign.analysis.dto.response;

import com.inha.capstonedesign.analysis.entity.age.AgeGroup;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Candidate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;

@Getter
public class CandidateForAnalysisResponseDto {

    @Schema(description = "분석에 사용될 후보자별 최종 성적의 성별 관련 정보")
    @Builder
    @AllArgsConstructor
    @Getter
    public static class BasedGender {
        @Schema(description = "후보자의 DB 에 기록된 ID", example = "1")
        private Long candidateId;
        @Schema(description = "후보자 이름", example = "주우민")
        private String candidateName;
        @Schema(description = "후보자 득표수", example = "10")
        private Integer candidateVoteCount;
        @Schema(description = "이미지 URL", example = "www.image.url")
        private String candidateImage;
        @Schema(description = "성별별 득표율", example = "{\"남성\": 33.33333, \"여성\": 39.33333}")
        private Map<Gender, Double> genderPercentage = new EnumMap<>(Gender.class);
        @Schema(description = "성별별 득표수", example = "{\"남성\": 3, \"여성\": 3}")
        private Map<Gender, Long> genderVoteCount = new EnumMap<>(Gender.class);

        public static BasedGender of(Candidate candidate, Map<Gender, Double> genderPercentage, Map<Gender, Long> genderVoteCount) {
            BasedGenderBuilder builder = BasedGender.builder()
                    .candidateId(candidate.getCandidateId())
                    .candidateName(candidate.getCandidateName())
                    .candidateVoteCount(candidate.getCandidateVoteCount())
                    .genderPercentage(genderPercentage)
                    .genderVoteCount(genderVoteCount);

            if (candidate.getCandidateImage() != null) {
                builder.candidateImage(candidate.getCandidateImage().getImagePath());
            }
            return builder.build();
        }
    }

    @Schema(description = "분석에 사용될 후보자별 최종 성적의 지역 관련 정보")
    @Builder
    @AllArgsConstructor
    @Getter
    public static class BasedRegion {
        @Schema(description = "후보자의 DB 에 기록된 ID", example = "1")
        private Long candidateId;
        @Schema(description = "후보자 이름", example = "주우민")
        private String candidateName;
        @Schema(description = "후보자 득표수", example = "10")
        private Integer candidateVoteCount;
        @Schema(description = "이미지 URL", example = "www.image.url")
        private String candidateImage;
        @Schema(description = "지역별 득표율", example = "{\"서울\": 33.33333, \"인천\": 33.33333, \"경기도\": 33.33333, \"강원도\": 33.33333, \"충청북도\": 33.33333, \"충청남도\": 33.33333, \"전라북도\": 33.33333, \"전라남도\": 33.33333, \"경상북도\": 33.33333, \"경상남도\": 33.33333}")
        private Map<Region, Double> regionPercentage = new EnumMap<>(Region.class);
        @Schema(description = "지역별 득표수", example = "{\"서울\": 3, \"인천\": 3, \"경기도\": 3, \"강원도\": 3, \"충청북도\": 3, \"충청남도\": 3, \"전라북도\": 3, \"전라남도\": 3, \"경상북도\": 3, \"경상남도\": 3}")
        private Map<Region, Long> regionVoteCount = new EnumMap<>(Region.class);

        public static BasedRegion of(Candidate candidate, Map<Region, Double> regionPercentage, Map<Region, Long> regionVoteCount) {
            BasedRegionBuilder builder = BasedRegion.builder()
                    .candidateId(candidate.getCandidateId())
                    .candidateName(candidate.getCandidateName())
                    .candidateVoteCount(candidate.getCandidateVoteCount())
                    .regionPercentage(regionPercentage)
                    .regionVoteCount(regionVoteCount);

            if (candidate.getCandidateImage() != null) {
                builder.candidateImage(candidate.getCandidateImage().getImagePath());
            }
            return builder.build();
        }
    }

    @Schema(description = "분석에 사용될 후보자별 최종 성적의 연령대 관련 정보")
    @Builder
    @AllArgsConstructor
    @Getter
    public static class BasedAgeGroup {
        @Schema(description = "후보자의 DB 에 기록된 ID", example = "1")
        private Long candidateId;
        @Schema(description = "후보자 이름", example = "주우민")
        private String candidateName;
        @Schema(description = "후보자 득표수", example = "10")
        private Integer candidateVoteCount;
        @Schema(description = "이미지 URL", example = "www.image.url")
        private String candidateImage;
        @Schema(description = "지역별 득표율", example = "{\"10대이하\": 33.33333, \"20대\": 33.33333, \"30대\": 33.33333, \"40대\": 33.33333, \"50대\": 33.33333, \"60대\": 33.33333, \"70대 이상\": 33.33333}")
        private Map<AgeGroup, Double> ageGroupPercentage = new EnumMap<>(AgeGroup.class);
        @Schema(description = "지역별 득표수", example = "{\"10대이하\": 3, \"20대\": 3, \"30대\": 3, \"40대\": 3, \"50대\": 3, \"60대\": 3, \"70대 이상\": 3}")
        private Map<AgeGroup, Long> ageGroupVoteCount = new EnumMap<>(AgeGroup.class);

        public static BasedAgeGroup of(Candidate candidate, Map<AgeGroup, Double> ageGroupPercentage, Map<AgeGroup, Long> ageGroupVoteCount) {
            BasedAgeGroupBuilder builder = BasedAgeGroup.builder()
                    .candidateId(candidate.getCandidateId())
                    .candidateName(candidate.getCandidateName())
                    .candidateVoteCount(candidate.getCandidateVoteCount())
                    .ageGroupPercentage(ageGroupPercentage)
                    .ageGroupVoteCount(ageGroupVoteCount);

            if (candidate.getCandidateImage() != null) {
                builder.candidateImage(candidate.getCandidateImage().getImagePath());
            }
            return builder.build();
        }
    }
}
