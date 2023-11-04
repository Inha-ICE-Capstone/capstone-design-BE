package com.inha.capstonedesign.voting.dto.response;

import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.Candidate;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BallotResponseDto {

    @Schema(name = "BallotResponseDto.Page", description = "투표 리스트 페이징 Request DTO")
    @Getter
    public static class Page {
        @Schema(description = "투표의 DB 에 기록된 ID", example = "1")
        private Long ballotId;
        @Schema(description = "투표 제목", example = "대선 투표")
        private String ballotName;
        @Schema(description = "투표 시작 시간", example = "2023-10-31T19:00:00")
        private LocalDateTime ballotStartDateTime;
        @Schema(description = "투표 종료 시간", example = "2023-11-11T19:00:00")
        private LocalDateTime ballotEndDateTime;
        @Schema(description = "투표 최소 나이", example = "20", nullable = true)
        private Integer ballotMinAge;
        @Schema(description = "투표 최대 나이", example = "100", nullable = true)
        private Integer ballotMaxAge;
        @Schema(description = "투표 가능 지역민", example = "경기도", allowableValues = {"null", "서울", "인천", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도"})
        private String ballotSubjectRegion;
        @Schema(description = "투표 가능 성별", example = "남성", allowableValues = {"null", "남성", "여성"})
        private String ballotSubjectGender;
        @Schema(description = "투표 간략 설명", example = "2022 대선 투표 입니다.")
        private String ballotBriefDescription;
        @Schema(description = "이미지 URL", example = "www.image.url")
        private String ballotImage;
        @Schema(description = "투표 상태", allowableValues = {"진행중", "시작전", "마감"})
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

    @Schema(name = "BallotResponseDto.Detail", description = "투표 상세 정보 Request DTO")
    @Getter
    public static class Detail {
        @Schema(description = "투표 제목", example = "대선 투표")
        private String ballotName;
        @Schema(description = "투표 시작 시간", example = "2023-10-31T19:00:00")
        private LocalDateTime ballotStartDateTime;
        @Schema(description = "투표 종료 시간", example = "2023-11-11T19:00:00")
        private LocalDateTime ballotEndDateTime;
        @Schema(description = "투표 최소 나이", example = "20", nullable = true)
        private Integer ballotMinAge;
        @Schema(description = "투표 최대 나이", example = "100", nullable = true)
        private Integer ballotMaxAge;
        @Schema(description = "투표 가능 지역민", example = "경기도", allowableValues = {"null", "서울", "인천", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도"})
        private String ballotSubjectRegion;
        @Schema(description = "투표 가능 성별", example = "남성", allowableValues = {"null", "남성", "여성"})

        private String ballotSubjectGender;
        @Schema(description = "투표 간략 설명", example = "2022 대선 투표 입니다.")
        private String ballotBriefDescription;
        @Schema(description = "투표 상세 설명", example = "2022 대선 투표 입니다. 대상은 누구고 ~~~ 상세한 설명")
        private String ballotDetailDescription;
        @ArraySchema(arraySchema = @Schema(description = "후보자 리스트"),
                uniqueItems = true,
                minItems = 0,
                schema = @Schema(implementation = CandidateResponseDto.Detail.class))
        private List<CandidateResponseDto.Detail> candidates = new ArrayList<>();
        @Schema(description = "이미지 URL", example = "www.image.url")
        private String ballotImage;
        @Schema(description = "투표 상태", allowableValues = {"진행중", "시작전", "마감"})
        private String ballotStatus;
        @Schema(description = "로그인 되어있는 사람이 대상인지 여부", allowableValues = {"true", "false"})
        private Boolean isSubject;
        @Schema(description = "로그인 되어있는 사람이 이미 투표를 했는지 여부", allowableValues = {"true", "false"})
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
