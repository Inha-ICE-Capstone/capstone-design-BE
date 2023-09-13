package com.inha.capstonedesign.voting.exception;

import com.inha.capstonedesign.global.exception.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum VotingExceptionType implements CustomExceptionType {

    BALLOT_END_TIME_BEFORE_START_TIME(HttpStatus.BAD_REQUEST, "V001", "투표 종료일이 시작일 이전입니다"),
    BALLOT_NOT_EXISTS(HttpStatus.BAD_REQUEST, "V002", "존재하지 않는 투표입니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}