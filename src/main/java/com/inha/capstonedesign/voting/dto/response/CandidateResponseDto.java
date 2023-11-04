package com.inha.capstonedesign.voting.dto.response;

import com.inha.capstonedesign.voting.entity.Candidate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class CandidateResponseDto {

    @Schema(name = "CandidateResponseDto.Detail",description = "후보자 상세 Request DTO")
    @Getter
    public static class Detail {
        @Schema(description = "후보자의 DB 에 기록된 ID", example = "1")
        private Long candidateId;
        @Schema(description = "후보자 이름", example = "주우민")
        private String candidateName;
        @Schema(description = "후보자 득표수", example = "10")
        private Integer candidateVoteCount;
        @Schema(description = "이미지 URL", example = "www.image.url")
        private String candidateImage;

        @Builder
        public Detail(Long candidateId, String candidateName, Integer candidateVoteCount, String candidateImage) {
            this.candidateId = candidateId;
            this.candidateName = candidateName;
            this.candidateVoteCount = candidateVoteCount;
            this.candidateImage = candidateImage;
        }

        public static Detail of(Candidate candidate) {
            DetailBuilder detailBuilder = Detail.builder()
                    .candidateId(candidate.getCandidateId())
                    .candidateName(candidate.getCandidateName())
                    .candidateVoteCount(candidate.getCandidateVoteCount());

            if (candidate.getCandidateImage() != null) {
                detailBuilder.candidateImage(candidate.getCandidateImage().getImagePath());
            }
            return detailBuilder.build();
        }
    }
}
