package com.inha.capstonedesign.analysis.controller;

import com.inha.capstonedesign.analysis.dto.response.CandidateForAnalysisResponseDto;
import com.inha.capstonedesign.analysis.service.AnalysisService;
import com.inha.capstonedesign.global.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "analysis", description = "투표 분석 API")
//swagger
@RestController
@RequiredArgsConstructor
@RequestMapping("/voting/analysis")
public class AnalysisController {
    private final AnalysisService analysisService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "후보자들의 성별 득표율", description = "선택한 투표 내의 후보자들의 남성 여성 득표율, 득표수를 반환")
    @Parameters({
            @Parameter(name = "ballotId", description = "투표의 DB Id", example = "1"),
    })
    @GetMapping("/gender-based/{ballotId}")
    public ResponseEntity<List<CandidateForAnalysisResponseDto.BasedGender>> getGenderAnalysis(@PathVariable Long ballotId) {
        List<CandidateForAnalysisResponseDto.BasedGender> genderAnalysis = analysisService.getGenderAnalysis(ballotId);
        return ResponseEntity.ok(genderAnalysis);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "후보자들의 지역 득표율", description = "선택한 투표 내의 후보자들의 지역별 득표율, 득표수를 반환")
    @Parameters({
            @Parameter(name = "ballotId", description = "투표의 DB Id", example = "1"),
    })
    @GetMapping("/region-based/{ballotId}")
    public ResponseEntity<List<CandidateForAnalysisResponseDto.BasedRegion>> getRegionAnalysis(@PathVariable Long ballotId) {
        List<CandidateForAnalysisResponseDto.BasedRegion> regionAnalysis = analysisService.getRegionAnalysis(ballotId);
        return ResponseEntity.ok(regionAnalysis);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "후보자들의 연령대 득표율", description = "선택한 투표 내의 후보자들의 연령대별 득표율, 득표수를 반환")
    @Parameters({
            @Parameter(name = "ballotId", description = "투표의 DB Id", example = "1"),
    })
    @GetMapping("/age-based/{ballotId}")
    public ResponseEntity<List<CandidateForAnalysisResponseDto.BasedAgeGroup>> getAgeGroupAnalysis(@PathVariable Long ballotId) {
        List<CandidateForAnalysisResponseDto.BasedAgeGroup> ageGroupAnalysis = analysisService.getAgeGroupAnalysis(ballotId);
        return ResponseEntity.ok(ageGroupAnalysis);
    }
}