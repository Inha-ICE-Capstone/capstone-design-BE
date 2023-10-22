package com.inha.capstonedesign.member.service;

import com.inha.capstonedesign.auth.exception.AuthException;
import com.inha.capstonedesign.auth.exception.AuthExceptionType;
import com.inha.capstonedesign.global.response.PageResponseDto;
import com.inha.capstonedesign.member.dto.response.MemberResponseDto;
import com.inha.capstonedesign.member.entity.Member;
import com.inha.capstonedesign.member.repository.MemberRepository;
import com.inha.capstonedesign.voting.dto.response.BallotResponseDto;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.VotingRecord;
import com.inha.capstonedesign.voting.entity.VotingRecordStatus;
import com.inha.capstonedesign.voting.repository.VotingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final VotingRecordRepository votingRecordRepository;

    public MemberResponseDto getMyInfo(String email) {
        final Member member = memberRepository.findByMemberWithImage(email)
                .orElseThrow(() -> new AuthException(AuthExceptionType.ACCOUNT_NOT_EXISTS));

        return MemberResponseDto.of(member);

    }

    public PageResponseDto<BallotResponseDto.RecordPage> getMyVoteRecords(Pageable pageable, String email) {
        final Member member = memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new AuthException(AuthExceptionType.ACCOUNT_NOT_EXISTS));
        Page<VotingRecord> votingRecords = votingRecordRepository.findByVoterOrderByVotingRecordIdDesc(member, pageable);

        List<BallotResponseDto.RecordPage> ballotRecords = votingRecords.getContent().stream()
                .map(votingRecord -> {
                    Ballot ballot = votingRecord.getBallot();
                    VotingRecordStatus status = votingRecord.getVotingRecordStatus();
                    return BallotResponseDto.RecordPage.of(ballot, status);
                })
                .collect(Collectors.toList());


        return new PageResponseDto<>(new PageImpl<>(ballotRecords, pageable, votingRecords.getTotalElements()));
    }
}
