package com.inha.capstonedesign.voting.service;

import com.inha.capstonedesign.global.web3j.GasProvider;
import com.inha.capstonedesign.global.web3j.Web3jProperties;
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

    public List<String> getCandidateList() {
        try {
//            List <Voting.Candidate>candidateList = votingContract.getCandidateList().send();
//            List candidateNameList = new ArrayList<>();
//            for(Voting.Candidate candidate : candidateList) {
//                candidateNameList.add(candidate.getCandidateName());
//            }

            List<Voting.Candidate> send = votingContract.getCandidateList().send();
            List<String> candidateNameList = send.stream().map(Voting.Candidate::getCandidateName)
                    .collect(Collectors.toList());
            return candidateNameList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void vote(String candidateName) {
        try {
            long beforeTime = System.currentTimeMillis();
            System.out.println("======================================");
            votingContract.voteForCandidate(candidateName).send();
            long afterTime = System.currentTimeMillis();
            System.out.println("======================================");
            long secDiffTime = (afterTime - beforeTime)/1000;
            System.out.println("secDiffTime = " + secDiffTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCandidate(String candidateName) {
        try {
            votingContract.addCandidate(candidateName).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigInteger getTotalVotes(String candidateName) {
        try {
            BigInteger totalVotes = votingContract.totalVotes(candidateName).send();
            return totalVotes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(-9999L);
    }
}
