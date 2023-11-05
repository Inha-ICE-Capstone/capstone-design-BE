package com.inha.capstonedesign.analysis.dto.response;

import com.inha.capstonedesign.voting.entity.Candidate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
        @Schema(description = "남성에게 받은 표 수", example = "3")
        private Long maleVoteCount;
        @Schema(description = "여성에게 받은 표 수", example = "3")
        private Long femaleVoteCount;
        @Schema(description = "남성에게 받은 득표율", example = "33.333333")
        private double maleVotePercentage;
        @Schema(description = "여성에게 받은 득표율", example = "33.333333")
        private double femaleVotePercentage;

        public static BasedGender of(Candidate candidate, Long maleVoteCount, Long femaleVoteCount, double maleVotePercentage, double femaleVotePercentage) {
            BasedGenderBuilder builder = BasedGender.builder()
                    .candidateId(candidate.getCandidateId())
                    .candidateName(candidate.getCandidateName())
                    .candidateVoteCount(candidate.getCandidateVoteCount())
                    .maleVoteCount(maleVoteCount)
                    .femaleVoteCount(femaleVoteCount)
                    .maleVotePercentage(maleVotePercentage)
                    .femaleVotePercentage(femaleVotePercentage);

            if (candidate.getCandidateImage() != null) {
                builder.candidateImage(candidate.getCandidateImage().getImagePath());
            }
            return builder.build();
        }

    }
}
