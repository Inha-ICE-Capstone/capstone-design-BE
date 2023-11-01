package com.inha.capstonedesign.voting.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Ballot;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "새 투표 생성 Request DTO")
@Getter
public class BallotRequestDto {
    @Schema(description = "투표 제목", example = "대선 투표")
    @NotNull
    private String ballotName;
    @Schema(description = "투표 시작 시간", example = "2023-10-31T19:00:00")
    @NotNull
    private LocalDateTime ballotStartDateTime;
    @Schema(description = "투표 종료 시간", example = "2023-11-11T19:00:00")
    @NotNull
    private LocalDateTime ballotEndDateTime;
    @Schema(description = "투표 최소 나이", example = "20")
    private Integer ballotMinAge;
    @Schema(description = "투표 최대 나이", example = "100")
    private Integer ballotMaxAge;
    @Schema(description = "투표 가능 지역민", example = "경기도", allowableValues = {"전국", "서울", "인천", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도"})
    private String ballotSubjectRegion;
    @Schema(description = "투표 가능 성별", example = "남성", allowableValues = {"성별 무관", "남성", "여성"})
    private String ballotSubjectGender;
    @Schema(description = "투표 간략 설명", example = "2022 대선 투표 입니다.")
    @NotNull
    private String ballotBriefDescription;
    @Schema(description = "투표 상세 설명", example = "2022 대선 투표 입니다. 대상은 누구고 ~~~ 상세한 설명")
    @NotNull
    private String ballotDetailDescription;

    @JsonIgnore
    private Region setEnumSubjectRegion(String region) {
        if (region.equals(Region.GYEONGGI.getKorean())) {
            return Region.GYEONGGI;
        } else if (region.equals(Region.INCHEON.getKorean())) {
            return Region.INCHEON;
        } else if (region.equals(Region.GANGWON.getKorean())) {
            return Region.GANGWON;
        } else if (region.equals(Region.CHUNGCHEONGBUK.getKorean())) {
            return Region.CHUNGCHEONGBUK;
        } else if (region.equals(Region.CHUNGCHEONGNAM.getKorean())) {
            return Region.CHUNGCHEONGNAM;
        } else if (region.equals(Region.JEOLLABUK.getKorean())) {
            return Region.JEOLLABUK;
        } else if (region.equals(Region.JEOLLANAM.getKorean())) {
            return Region.JEOLLANAM;
        } else if (region.equals(Region.GYEONGSANGBUK.getKorean())) {
            return Region.GYEONGSANGBUK;
        } else if (region.equals(Region.GYEONGSANGNAM.getKorean())) {
            return Region.GYEONGSANGNAM;
        } else if (region.equals(Region.SEOUL.getKorean())) {
            return Region.SEOUL;
        }
        return null;
    }

    @JsonIgnore
    private Gender setEnumSubjectGender(String gender) {
        if (gender.equals(Gender.MALE.getKorean())) {
            return Gender.MALE;
        } else if (gender.equals(Gender.FEMALE.getKorean())) {
            return Gender.FEMALE;
        }
        return null;
    }

    public Ballot toEntity() {
        Ballot.BallotBuilder ballotBuilder = Ballot.builder()
                .ballotName(ballotName)
                .ballotStartDateTime(ballotStartDateTime)
                .ballotEndDateTime(ballotEndDateTime)
                .ballotMinAge(ballotMinAge)
                .ballotMaxAge(ballotMaxAge)
                .ballotBriefDescription(ballotBriefDescription)
                .ballotDetailDescription(ballotDetailDescription);

        if (ballotSubjectRegion != null) {
            ballotBuilder.ballotSubjectRegion(setEnumSubjectRegion(ballotSubjectRegion));
        }

        if (ballotSubjectGender != null) {
            ballotBuilder.ballotSubjectGender(setEnumSubjectGender(ballotSubjectGender));
        }
        return ballotBuilder.build();
    }
}
