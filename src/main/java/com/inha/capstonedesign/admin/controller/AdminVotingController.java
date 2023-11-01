package com.inha.capstonedesign.admin.controller;

import com.inha.capstonedesign.global.response.ErrorResponse;
import com.inha.capstonedesign.voting.dto.request.BallotRequestDto;
import com.inha.capstonedesign.voting.dto.request.CandidateRequestDto;
import com.inha.capstonedesign.voting.service.VotingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Tag(name = "admins(voting)", description = "관리자의 투표 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/voting")
public class AdminVotingController {

    private final VotingService votingService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "생성 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "새 투표 생성", description = "관리자가 시작하기 전의 새 투표를 생성")
    @PostMapping(value = "/ballots", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addBallot(@RequestPart @Valid BallotRequestDto ballotRequestDto,
                                            @Parameter(name = "ballotImage", description = "투표의 대표 이미지") @RequestPart MultipartFile ballotImage) throws IOException {
        votingService.addBallot(ballotRequestDto, ballotImage);
        return ResponseEntity.ok(null);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "생성 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "새 후보자 생성", description = "투표내의 새 후보자 생성")
    @PostMapping(value = "/candidates", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addCandidate(@RequestPart @Valid CandidateRequestDto candidateRequestDto,
                                               @Parameter(name = "candidateImage", description = "후보자의 대표 이미지")@RequestPart MultipartFile candidateImage) {
        votingService.addCandidate(candidateRequestDto, candidateImage);

        return ResponseEntity.ok(null);
    }
}
