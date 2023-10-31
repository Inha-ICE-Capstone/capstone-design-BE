package com.inha.capstonedesign.member.dto.response;

import com.inha.capstonedesign.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Schema(description = "회원 정보 Response DTO")
@Getter
@AllArgsConstructor
public class MemberResponseDto {

    @Schema(description = "이메일", example = "test@gmail.com")
    private String memberEmail;
    @Schema(description = "이름", example = "주우민")
    private String memberName;
    @Schema(description = "닉네임", example = "test")
    private String memberNickName;
    @Schema(description = "생년월일", example = "1999-01-28")
    private LocalDate memberBirthDate;
    @Schema(description = "나이", example = "24")
    private Integer memberAge;
    @Schema(description = "성별", example = "남성", allowableValues = {"남성", "여성"})
    private String memberGender;
    @Schema(description = "지역", example = "경기도", allowableValues = {"서울", "인천", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도"})
    private String memberRegion;
    @Schema(description = "이미지 URL", example = "www.image.url")
    private String memberImage;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
                member.getMemberEmail(),
                member.getMemberName(),
                member.getMemberNickName(),
                member.getMemberBirthDate(),
                member.getMemberAge(),
                member.getMemberGender().getKorean(),
                member.getMemberRegion().getKorean(),
                member.getMemberImage().getImagePath());
    }
}
