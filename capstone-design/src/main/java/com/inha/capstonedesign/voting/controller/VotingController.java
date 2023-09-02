package com.inha.capstonedesign.voting.controller;

import com.inha.capstonedesign.voting.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;

    @GetMapping("/candidate-list")
    public ResponseEntity<List<String>> getCandidateList() {
        return ResponseEntity.ok(votingService.getCandidateList());
    }

    @PostMapping("/add-candidate/{candidateName}")
    public ResponseEntity<String> addCandidate(@PathVariable String candidateName) {
        votingService.addCandidate(candidateName);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/{candidateName}")
    public ResponseEntity<String> vote(@PathVariable String candidateName) {
        votingService.vote(candidateName);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/total-votes/{candidateName}")
    public ResponseEntity<BigInteger> getTotalVotes(@PathVariable String candidateName) {
        return ResponseEntity.ok(votingService.getTotalVotes(candidateName));
    }
}
