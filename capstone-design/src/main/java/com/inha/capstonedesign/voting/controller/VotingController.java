package com.inha.capstonedesign.voting.controller;

import com.inha.capstonedesign.voting.dto.request.CandidateRequestDto;
import com.inha.capstonedesign.voting.dto.request.VoteRequestDto;
import com.inha.capstonedesign.voting.service.VotingService;
import com.inha.capstonedesign.voting.solidity.Voting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;

    @GetMapping("/ballots")
    public ResponseEntity<List<String>> getBallotList() {
        List<String> ballotList = votingService.getBallotList();

        return ResponseEntity.ok(ballotList);
    }
    @PostMapping("/ballots/{ballotName}")
    public ResponseEntity<String> addBallot(@PathVariable String ballotName) {
        votingService.addBallot(ballotName);

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

    @GetMapping("/total-votes")
    public ResponseEntity<BigInteger> getTotalVotes(@RequestBody @Valid VoteRequestDto voteDto) {
        return ResponseEntity.ok(votingService.getTotalVotes(voteDto));
    }


    @PostMapping
    public ResponseEntity<String> vote(@RequestBody @Valid VoteRequestDto voteDto) {
        votingService.vote(voteDto);

        return ResponseEntity.ok(null);
    }

}
