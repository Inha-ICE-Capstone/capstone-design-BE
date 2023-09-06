package com.inha.capstonedesign.voting.entity;

public enum BallotStatus {

    NOT_STARTED("아직 시작 안한 투표"),
    IN_PROGRESS("진행중인 투표"),
    CLOSED("마감된 투표");

    private String korean;

    public String getKorean() {
        return korean;
    }

    BallotStatus(String korean) {
        this.korean = korean;
    }
}
