package com.inha.capstonedesign.voting.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "ballot")
    private List<Candidate> candidates = new ArrayList<>();

    @OneToMany(mappedBy = "ballot")
    private List<VotingRecord> votingRecords = new ArrayList<>();
}
