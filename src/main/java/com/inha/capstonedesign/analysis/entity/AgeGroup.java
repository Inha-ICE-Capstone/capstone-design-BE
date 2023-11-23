package com.inha.capstonedesign.analysis.entity;

public enum AgeGroup {
    TEENS_OR_LESS("10대이하"),
    TWENTIES("20대"),
    THIRTIES("30대"),
    FORTIES("40대"),
    FIFTIES("50대"),
    SIXTIES_OR_ABOVE("60대이상");

    private String korean;

    public String getKorean() {
        return korean;
    }

    AgeGroup(String korean) {
        this.korean = korean;
    }
}
