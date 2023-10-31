package com.inha.capstonedesign.member.controller;

import com.inha.capstonedesign.global.response.ErrorResponse;
import com.inha.capstonedesign.global.response.PageResponseDto;
import com.inha.capstonedesign.member.dto.request.MemberRequestDto;
import com.inha.capstonedesign.member.dto.response.MemberResponseDto;
import com.inha.capstonedesign.member.service.MemberService;
import com.inha.capstonedesign.voting.dto.response.BallotResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.inha.capstonedesign.global.config.PageSizeConfig.BALLOT_RECORD_PAGE_SIZE;

@Tag(name = "members", description = "회원 API")
//swagger
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = MemberResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "회원 정보 조회", description = "자신의 회원 정보를 조회")
    @Parameters({
            @Parameter(name = "access", hidden = true)
    })
    //swagger
    @GetMapping("/members")
    public ResponseEntity<MemberResponseDto> getMyInfo(@AuthenticationPrincipal MemberRequestDto.Access access) {
        return ResponseEntity.ok(memberService.getMyInfo(access.getEmail()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "투표 기록 조회", description = "자신의 투표 기록을 조회")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)"),
            @Parameter(name = "access", hidden = true)
    })
    @GetMapping("/members/vote-records")
    public ResponseEntity<PageResponseDto<BallotResponseDto.RecordPage>> getMyVoteRecords(
            @RequestParam(required = false, defaultValue = "1") int page, @AuthenticationPrincipal MemberRequestDto.Access access) {
        if (page < 1) {
            page = 1;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, BALLOT_RECORD_PAGE_SIZE);

        return ResponseEntity.ok(memberService.getMyVoteRecords(pageRequest, access.getEmail()));
    }
}
