package com.inha.capstonedesign.voting.dto.response;

import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.Candidate;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BallotResponseDto {

    private String ballotName;
    private LocalDateTime ballotStartDateTime;
    private LocalDateTime ballotEndDateTime;
    private List<Candidate> candidates = new ArrayList<>();
    private String ballotImage;
    private String ballotStatus;

    public BallotResponseDto(String ballotName, LocalDateTime ballotStartDateTime, LocalDateTime ballotEndDateTime, List<Candidate> candidates, String ballotImage, String ballotStatus) {
        this.ballotName = ballotName;
        this.ballotStartDateTime = ballotStartDateTime;
        this.ballotEndDateTime = ballotEndDateTime;
        this.candidates.addAll(candidates);
        this.ballotImage = ballotImage;
        this.ballotStatus = ballotStatus;
    }

    public static BallotResponseDto of(Ballot ballot) {
        return new BallotResponseDto(
                ballot.getBallotName(),
                ballot.getBallotStartDateTime(),
                ballot.getBallotEndDateTime(),
                ballot.getCandidates(),
                ballot.getBallotImage().getImagePath(),
                ballot.getBallotStatus().getKorean());
    }
}
