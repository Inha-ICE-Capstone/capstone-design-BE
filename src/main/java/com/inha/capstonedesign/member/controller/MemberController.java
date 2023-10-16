package com.inha.capstonedesign.member.controller;

import com.inha.capstonedesign.global.response.PageResponseDto;
import com.inha.capstonedesign.member.dto.request.MemberRequestDto;
import com.inha.capstonedesign.member.dto.response.MemberResponseDto;
import com.inha.capstonedesign.member.service.MemberService;
import com.inha.capstonedesign.voting.dto.response.BallotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.inha.capstonedesign.global.config.PageSizeConfig.BALLOT_PAGE_SIZE;
import static com.inha.capstonedesign.global.config.PageSizeConfig.BALLOT_RECORD_PAGE_SIZE;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<MemberResponseDto> getMyInfo(@AuthenticationPrincipal MemberRequestDto.Access access) {
        return ResponseEntity.ok(memberService.getMyInfo(access.getEmail()));
    }

    @GetMapping("/members/vote-records")
    public ResponseEntity<PageResponseDto<BallotResponseDto.RecordPage>> getMyVoteRecords(
            @RequestParam(required = false, defaultValue = "1") int page, @AuthenticationPrincipal MemberRequestDto.Access access) {
        if (page < 1) {
            page = 1;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, BALLOT_RECORD_PAGE_SIZE);

        return ResponseEntity.ok(memberService.getMyVoteRecords(pageRequest, access.getEmail()));
    }
}
