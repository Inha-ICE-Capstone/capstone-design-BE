package com.inha.capstonedesign.voting.exception;

import com.inha.capstonedesign.global.exception.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum VotingExceptionType implements CustomExceptionType {

    BALLOT_NOT_EXISTS(HttpStatus.BAD_REQUEST, "V001", "존재하지 않는 투표입니다");

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