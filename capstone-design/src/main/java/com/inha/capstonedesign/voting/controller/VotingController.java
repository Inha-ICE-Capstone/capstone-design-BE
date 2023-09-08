package com.inha.capstonedesign.voting.controller;

import com.inha.capstonedesign.global.response.PageResponseDto;
import com.inha.capstonedesign.voting.dto.request.BallotRequestDto;
import com.inha.capstonedesign.voting.dto.request.CandidateRequestDto;
import com.inha.capstonedesign.voting.dto.request.VoteRequestDto;
import com.inha.capstonedesign.voting.dto.response.BallotResponseDto;
import com.inha.capstonedesign.voting.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static com.inha.capstonedesign.global.config.PageSizeConfig.BALLOT_PAGE_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;

    @GetMapping("/ballots")
    public ResponseEntity<PageResponseDto<BallotResponseDto.Page>> getBallotList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "진행중") String status) {
        if (page < 1) {
            page = 1;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, BALLOT_PAGE_SIZE);
        PageResponseDto<BallotResponseDto.Page> ballotResponse = votingService.getBallotResponse(pageRequest, status);

        return ResponseEntity.ok(ballotResponse);
    }

    @GetMapping("/ballots/{ballotId}")
    public ResponseEntity<BallotResponseDto.Detail> getBallotDetail(@PathVariable Long ballotId) {
        return ResponseEntity.ok(votingService.getBallotDetail(ballotId));
    }

    @PostMapping("/ballots")
    public ResponseEntity<String> addBallot(@RequestPart @Valid BallotRequestDto ballotRequestDto,
                                            @RequestPart MultipartFile ballotImage) throws IOException {
        votingService.addBallot(ballotRequestDto, ballotImage);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/candidates/{ballotId}")
    public ResponseEntity<List<String>> getCandidateList(@PathVariable Integer ballotId) {
        return ResponseEntity.ok(votingService.getCandidateList(ballotId));
    }

    @PostMapping("/candidates")
    public ResponseEntity<String> addCandidate(@RequestBody @Valid CandidateRequestDto candidateDto) {
        votingService.addCandidate(candidateDto);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/vote-count")
    public ResponseEntity<BigInteger> getVoteCount(@RequestBody @Valid VoteRequestDto voteDto) {
        return ResponseEntity.ok(votingService.getVoteCount(voteDto));
    }


    @PostMapping
    public ResponseEntity<String> vote(@RequestBody @Valid VoteRequestDto voteDto) {
        votingService.vote(voteDto);

        return ResponseEntity.ok(null);
    }

}
