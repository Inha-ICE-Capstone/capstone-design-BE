package com.inha.capstonedesign.voting.dto.request;

import com.inha.capstonedesign.voting.entity.Ballot;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BallotRequestDto {
    private String ballotName;
    private LocalDateTime ballotStartDateTime;
    private LocalDateTime ballotEndDateTime;

    public Ballot toEntity() {
        return Ballot.builder()
                .ballotName(ballotName)
                .ballotStartDateTime(ballotStartDateTime)
                .ballotEndDateTime(ballotEndDateTime)
                .build();
    }
}
