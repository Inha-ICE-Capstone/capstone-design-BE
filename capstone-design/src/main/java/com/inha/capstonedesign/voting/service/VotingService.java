package com.inha.capstonedesign.voting.service;

import com.inha.capstonedesign.global.web3j.GasProvider;
import com.inha.capstonedesign.global.web3j.Web3jProperties;
import com.inha.capstonedesign.voting.dto.request.CandidateRequestDto;
import com.inha.capstonedesign.voting.dto.request.VoteRequestDto;
import com.inha.capstonedesign.voting.solidity.Voting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VotingService {

    private final GasProvider gasProvider;
    private final Web3jProperties web3jProperties;
    private Credentials credentials;
    private Web3j web3j;
    private Voting votingContract;

    @PostConstruct
    public void initialize() {
        credentials = Credentials.create(web3jProperties.getWalletPrivateKey());
        web3j = Web3j.build(new HttpService(web3jProperties.getRpcEndpointUrl()));
        votingContract = Voting.load(web3jProperties.getContractAddress(), web3j, credentials, gasProvider);
    }

    public List<String> getBallotList() {
        try {
            List<Voting.Ballot> ballotList = votingContract.getBallotList().send();
            List<String> ballotNameList = ballotList.stream().map(Voting.Ballot::getBallotName)
                    .collect(Collectors.toList());
            return ballotNameList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBallot(String ballotName) {
        try {
            votingContract.addBallot(ballotName).send();
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
            BigInteger totalVotes = votingContract.totalVotes(BigInteger.valueOf(voteDto.getBallotId()), voteDto.getCandidateName()).send();
            return totalVotes;
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
