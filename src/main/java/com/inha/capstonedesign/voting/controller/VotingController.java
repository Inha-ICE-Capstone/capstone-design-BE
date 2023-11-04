package com.inha.capstonedesign.voting.controller;

import com.inha.capstonedesign.global.response.ErrorResponse;
import com.inha.capstonedesign.global.response.PageResponseDto;
import com.inha.capstonedesign.member.dto.request.MemberRequestDto;
import com.inha.capstonedesign.voting.dto.request.VoteRequestDto;
import com.inha.capstonedesign.voting.dto.response.BallotResponseDto;
import com.inha.capstonedesign.voting.service.VotingService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.inha.capstonedesign.global.config.PageSizeConfig.BALLOT_PAGE_SIZE;

@Tag(name = "voting", description = "투표 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "투표 리스트 조회", description = "투표 리스트를 페이징 적용해서 조회")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)"),
            @Parameter(name = "status", description = "투표 상태", schema = @Schema(allowableValues = {"진행중", "시작전", "마감"}))
    })
    @GetMapping("/ballots")
    public ResponseEntity<PageResponseDto<BallotResponseDto.Page>> getBallotList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "진행중") String status) {
        if (page < 1) {
            page = 1;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, BALLOT_PAGE_SIZE);
        PageResponseDto<BallotResponseDto.Page> ballotResponse = votingService.getBallotPage(pageRequest, status);

        return ResponseEntity.ok(ballotResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "투표 상세 조회", description = "선택한 투표의 정보를 상세 조회")
    @Parameters({
            @Parameter(name = "ballotId", description = "투표의 DB Id", example = "1"),
            @Parameter(name = "access", hidden = true)
    })
    @GetMapping("/ballots/{ballotId}")
    public ResponseEntity<BallotResponseDto.Detail> getBallotDetail(@PathVariable Long ballotId,
                                                                    @AuthenticationPrincipal MemberRequestDto.Access access) {
        return ResponseEntity.ok(votingService.getBallotDetail(ballotId, access));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "투표 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "투표 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "투표 시도", description = "선택한 후보자에게 투표")
    @Parameters({
            @Parameter(name = "access", hidden = true)
    })
    @PostMapping
    public ResponseEntity<String> vote(@RequestBody @Valid VoteRequestDto voteDto,
                                       @AuthenticationPrincipal MemberRequestDto.Access access) {
        votingService.verifyAndUpdateVotingRecordStatus(voteDto, access);
        votingService.vote(voteDto, access);

        return ResponseEntity.ok(null);
    }

}
