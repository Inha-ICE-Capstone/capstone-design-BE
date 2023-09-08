package com.inha.capstonedesign.voting.service;

import com.inha.capstonedesign.global.web3j.GasProvider;
import com.inha.capstonedesign.global.web3j.Web3jProperties;
import com.inha.capstonedesign.image.ImageUploadService;
import com.inha.capstonedesign.image.entity.Image;
import com.inha.capstonedesign.voting.dto.request.BallotRequestDto;
import com.inha.capstonedesign.voting.dto.request.CandidateRequestDto;
import com.inha.capstonedesign.voting.dto.request.VoteRequestDto;
import com.inha.capstonedesign.voting.dto.response.BallotResponseDto;
import com.inha.capstonedesign.voting.entity.Ballot;
import com.inha.capstonedesign.voting.entity.BallotStatus;
import com.inha.capstonedesign.voting.repository.ballot.BallotRepository;
import com.inha.capstonedesign.voting.solidity.Voting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VotingService {

    private final GasProvider gasProvider;
    private final Web3jProperties web3jProperties;
    private Credentials credentials;
    private Web3j web3j;
    private Voting votingContract;

    private final BallotRepository ballotRepository;
    private final ImageUploadService imageUploadService;

    @PostConstruct
    public void initialize() {
        credentials = Credentials.create(web3jProperties.getWalletPrivateKey());
        web3j = Web3j.build(new HttpService(web3jProperties.getRpcEndpointUrl()));
        votingContract = Voting.load(web3jProperties.getContractAddress(), web3j, credentials, gasProvider);
    }

    public List<BallotResponseDto> getBallotList() {
        List<Ballot> ballots = ballotRepository.findAllByBallotStatusOrderByBallotEndDateTime(BallotStatus.NOT_STARTED);
        return ballots.stream().map(BallotResponseDto::of).collect(Collectors.toList());
    }

    @Transactional
    public void addBallot(BallotRequestDto ballotRequestDto, MultipartFile ballotImage) throws IOException {
        try {
            votingContract.addBallot(ballotRequestDto.getBallotName()).send();
            Ballot ballot = ballotRequestDto.toEntity();

            Image image = imageUploadService.uploadImage(ballotImage);
            ballot.setBallotImage(image.toBallotImage());
            ballotRepository.save(ballot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getCandidateList(Integer ballotId) {
        try {

            List<Voting.Candidate> send = votingContract.getCandidateList(BigInteger.valueOf(ballotId)).send();
            List<String> candidateNameList = send.stream().map(Voting.Candidate::getName)
                    .collect(Collectors.toList());
            return candidateNameList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addCandidate(CandidateRequestDto candidateDto) {
        try {
            votingContract.addCandidate(BigInteger.valueOf(candidateDto.getBallotId()), candidateDto.getCandidateName()).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigInteger getVoteCount(VoteRequestDto voteDto) {
        try {
            BigInteger voteCount = votingContract.getVoteCount(BigInteger.valueOf(voteDto.getBallotId()), voteDto.getCandidateName()).send();
            return voteCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(-9999L);
    }

    public void vote(VoteRequestDto voteDto) {
        try {
            votingContract.voteForCandidate(BigInteger.valueOf(voteDto.getBallotId()), voteDto.getCandidateName()).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
