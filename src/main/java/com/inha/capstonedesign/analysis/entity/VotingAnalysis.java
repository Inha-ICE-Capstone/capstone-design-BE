package com.inha.capstonedesign.analysis.entity;

import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Region;
import com.inha.capstonedesign.voting.entity.Candidate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "voting_analysis")
public class VotingAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voting_analysis_id")
    private Long votingAnalysisId;

    @Column(name = "ageGroup")
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "region")
    @Enumerated(EnumType.STRING)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public VotingAnalysis(AgeGroup ageGroup, Gender gender, Region region, Candidate candidate) {
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.region = region;
        this.candidate = candidate;
    }
}
