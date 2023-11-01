package com.inha.capstonedesign.voting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Schema(description = "새 후보자 생성 Request DTO")
@Getter
public class CandidateRequestDto {

    @Schema(description = "후보자를 생성할 투표 Id", example = "1")
    @NotNull
    private Long ballotId;
    @Schema(description = "후보자 이름", example = "주우민")
    @NotNull
    private String candidateName;
}
