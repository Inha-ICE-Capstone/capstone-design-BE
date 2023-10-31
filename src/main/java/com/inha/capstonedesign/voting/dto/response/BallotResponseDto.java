package com.inha.capstonedesign.voting.dto.response;

import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BallotResponseDto {

    @Getter
    public static class Page {
        private Long ballotId;
        private String ballotName;
        private LocalDateTime ballotStartDateTime;
        private LocalDateTime ballotEndDateTime;
        private Integer ballotMinAge;
        private Integer ballotMaxAge;
        private String ballotSubjectRegion;
        private String ballotSubjectGender;
        private String ballotBriefDescription;
        private String ballotImage;
        private String ballotStatus;

        @Builder
        public Page(Long ballotId, String ballotName, LocalDateTime ballotStartDateTime, LocalDateTime ballotEndDateTime, Integer ballotMinAge, Integer ballotMaxAge, String ballotSubjectRegion, String ballotSubjectGender, String ballotBriefDescription, String ballotImage, String ballotStatus) {
            this.ballotId = ballotId;
            this.ballotName = ballotName;
            this.ballotStartDateTime = ballotStartDateTime;
            this.ballotEndDateTime = ballotEndDateTime;
            this.ballotMinAge = ballotMinAge;
            this.ballotMaxAge = ballotMaxAge;
            this.ballotSubjectRegion = ballotSubjectRegion;
            this.ballotSubjectGender = ballotSubjectGender;
            this.ballotBriefDescription = ballotBriefDescription;
            this.ballotImage = ballotImage;
            this.ballotStatus = ballotStatus;
        }

        public static Page of(Ballot ballot) {
            PageBuilder ballotResponseDtoBuilder = Page.builder()
                    .ballotId(ballot.getBallotId())
                    .ballotName(ballot.getBallotName())
                    .ballotStartDateTime(ballot.getBallotStartDateTime())
                    .ballotEndDateTime(ballot.getBallotEndDateTime())
                    .ballotMinAge(ballot.getBallotMinAge())
                    .ballotMaxAge(ballot.getBallotMaxAge())
                    .ballotBriefDescription(ballot.getBallotBriefDescription())
                    .ballotStatus(ballot.getBallotStatus().getKorean());

            if (ballot.getBallotSubjectRegion() != null) {
                ballotResponseDtoBuilder.ballotSubjectRegion(ballot.getBallotSubjectRegion().getKorean());
            }
            if (ballot.getBallotSubjectGender() != null) {
                ballotResponseDtoBuilder.ballotSubjectGender(ballot.getBallotSubjectGender().getKorean());
            }
            if (ballot.getBallotImage() != null) {
                ballotResponseDtoBuilder.ballotImage(ballot.getBallotImage().getImagePath());
            }
            return ballotResponseDtoBuilder.build();
        }
    }

    @Getter
    public static class Detail {
        private String ballotName;
        private LocalDateTime ballotStartDateTime;
        private LocalDateTime ballotEndDateTime;
        private Integer ballotMinAge;
        private Integer ballotMaxAge;
        private String ballotSubjectRegion;
        private String ballotSubjectGender;
        private String ballotBriefDescription;
        private String ballotDetailDescription;
        private List<CandidateResponseDto.Detail> candidates = new ArrayList<>();
        private String ballotImage;
        private String ballotStatus;
        private Boolean isSubject;
        private Boolean notVoted;

        @Builder
        public Detail(String ballotName, LocalDateTime ballotStartDateTime, LocalDateTime ballotEndDateTime, Integer ballotMinAge, Integer ballotMaxAge, String ballotSubjectRegion, String ballotSubjectGender, String ballotBriefDescription, String ballotDetailDescription, List<Candidate> candidates, String ballotImage, String ballotStatus, Boolean isSubject, Boolean notVoted) {
            this.ballotName = ballotName;
            this.ballotStartDateTime = ballotStartDateTime;
            this.ballotEndDateTime = ballotEndDateTime;
            this.ballotMinAge = ballotMinAge;
            this.ballotMaxAge = ballotMaxAge;
            this.ballotSubjectRegion = ballotSubjectRegion;
            this.ballotSubjectGender = ballotSubjectGender;
            this.ballotBriefDescription = ballotBriefDescription;
            this.ballotDetailDescription = ballotDetailDescription;
            this.candidates.addAll(
                    candidates.stream().map(CandidateResponseDto.Detail::of).collect(Collectors.toList()));
            this.ballotImage = ballotImage;
            this.ballotStatus = ballotStatus;
            this.isSubject = isSubject;
            this.notVoted = notVoted;
        }

        public static Detail of(Ballot ballot, Boolean isSubject, Boolean notVoted) {

            Detail.DetailBuilder detailBuilder = Detail.builder()
                    .ballotName(ballot.getBallotName())
                    .ballotStartDateTime(ballot.getBallotStartDateTime())
                    .ballotEndDateTime(ballot.getBallotEndDateTime())
                    .ballotMinAge(ballot.getBallotMinAge())
                    .ballotMaxAge(ballot.getBallotMaxAge())
                    .ballotBriefDescription(ballot.getBallotBriefDescription())
                    .ballotDetailDescription(ballot.getBallotDetailDescription())
                    .candidates(ballot.getCandidates())
                    .ballotStatus(ballot.getBallotStatus().getKorean())
                    .isSubject(isSubject)
                    .notVoted(notVoted);

            if (ballot.getBallotSubjectRegion() != null) {
                detailBuilder.ballotSubjectRegion(ballot.getBallotSubjectRegion().getKorean());
            }
            if (ballot.getBallotSubjectGender() != null) {
                detailBuilder.ballotSubjectGender(ballot.getBallotSubjectGender().getKorean());
            }
            if (ballot.getBallotImage() != null) {
                detailBuilder.ballotImage(ballot.getBallotImage().getImagePath());
            }
            return detailBuilder.build();
        }
    }

    @Schema(description = "자신의 투표 기록 Response DTO")
    @Getter
    public static class RecordPage {
        @Schema(description = "DB의 ID", example = "1")
        private Long ballotId;
        @Schema(description = "투표 제목", example = "대선 투표")
        private String ballotName;
        @Schema(description = "투표 간략 설명", example = "2022 대선 투표 입니다.")
        private String ballotBriefDescription;
        @Schema(description = "이미지 URL", example = "www.image.url")
        private String ballotImage;
        @Schema(description = "투표 기록 상태", example = "투표 완료", allowableValues = {"투표 진행중", "투표 완료", "오류로 인한 투표 취소"})
        private String votingRecordStatus;

        @Builder
        public RecordPage(Long ballotId, String ballotName, String ballotBriefDescription, String ballotImage, String votingRecordStatus) {
            this.ballotId = ballotId;
            this.ballotName = ballotName;
            this.ballotBriefDescription = ballotBriefDescription;
            this.ballotImage = ballotImage;
            this.votingRecordStatus = votingRecordStatus;
        }

        public static RecordPage of(Ballot ballot, VotingRecordStatus votingRecordStatus) {
            RecordPageBuilder ballotResponseDtoBuilder = RecordPage.builder()
                    .ballotId(ballot.getBallotId())
                    .ballotName(ballot.getBallotName())
                    .ballotBriefDescription(ballot.getBallotBriefDescription())
                    .votingRecordStatus(votingRecordStatus.getKorean());

            if (ballot.getBallotImage() != null) {
                ballotResponseDtoBuilder.ballotImage(ballot.getBallotImage().getImagePath());
            }

            return ballotResponseDtoBuilder.build();
        }
    }
}
