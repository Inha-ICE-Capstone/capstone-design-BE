package com.inha.capstonedesign.voting.entity;

import com.inha.capstonedesign.image.entity.BallotImage;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ballot")
public class Ballot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ballot_id")
    private Long ballotId;

    @Column(name = "ballot_name")
    private String ballotName;

    @Column(name = "ballot_start_date_time")
    private LocalDateTime ballotStartDateTime;

    @Column(name = "ballot_end_date_time")
    private LocalDateTime ballotEndDateTime;

    @Column(name = "ballot_status")
    @Enumerated(EnumType.STRING)
    private BallotStatus ballotStatus;


    @OneToMany(mappedBy = "ballot")
    private List<Candidate> candidates = new ArrayList<>();

    @OneToMany(mappedBy = "ballot")
    private List<VotingRecord> votingRecords = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ballot_image_id")
    @Setter
    private BallotImage ballotImage;

    public void changeBallotStatus(BallotStatus ballotStatus) {
        this.ballotStatus = ballotStatus;
    }

    @Builder
    public Ballot(String ballotName, LocalDateTime ballotStartDateTime, LocalDateTime ballotEndDateTime) {
        this.ballotName = ballotName;
        this.ballotStartDateTime = ballotStartDateTime;
        this.ballotEndDateTime = ballotEndDateTime;
        this.ballotStatus = BallotStatus.NOT_STARTED;
    }
}
