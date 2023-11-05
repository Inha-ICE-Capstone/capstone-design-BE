package com.inha.capstonedesign.analysis.dto.response;

import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.voting.entity.Candidate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
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
        private Map<Gender, Double> genderPercentage = new HashMap<>();
        @Schema(description = "성별별 득표수", example = "{\"남성\": 3, \"여성\": 3}")
        private Map<Gender, Long> genderVoteCount = new HashMap<>();

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
}
