package com.inha.capstonedesign.voting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Schema(description = "투표 시도 Request Dto")
@Getter
public class VoteRequestDto {
    @Schema(description = "투표의 DB 에 기록된 ID", example = "1")
    @NotNull
    private Long ballotId;
    @Schema(description = "후보자의 DB 에 기록된 ID", example = "1")
    @NotNull
    private Long candidateId;
}
