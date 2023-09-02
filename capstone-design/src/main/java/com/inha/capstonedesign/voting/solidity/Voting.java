package com.inha.capstonedesign.voting.solidity;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.0.
 */
@SuppressWarnings("rawtypes")
public class Voting extends Contract {
    public static final String BINARY = "0x608060405234801561000f575f80fd5b50610f9c8061001d5f395ff3fe608060405234801561000f575f80fd5b5060043610610055575f3560e01c8063462e91ec146100595780635900f8f814610075578063b13c744b146100a5578063e89927ef146100d6578063fdbc4006146100f2575b5f80fd5b610073600480360381019061006e9190610759565b610110565b005b61008f600480360381019061008a9190610759565b6101d1565b60405161009c91906107bb565b60405180910390f35b6100bf60048036038101906100ba9190610807565b6102bf565b6040516100cd9291906108ac565b60405180910390f35b6100f060048036038101906100eb9190610759565b61037f565b005b6100fa61048a565b6040516101079190610a26565b60405180910390f35b61011981610590565b15610159576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161015090610a90565b60405180910390fd5b5f60405180604001604052808381526020015f60ff16815250908060018154018082558091505060019003905f5260205f2090600202015f909190919091505f820151815f0190816101ab9190610ca8565b506020820151816001015f6101000a81548160ff021916908360ff160217905550505050565b5f6101db82610590565b61021a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161021190610dc1565b60405180910390fd5b5f5b5f805490508110156102b55782805190602001205f828154811061024357610242610ddf565b5b905f5260205f2090600202015f0160405161025e9190610ea8565b6040518091039020036102a2575f818154811061027e5761027d610ddf565b5b905f5260205f2090600202016001015f9054906101000a900460ff169150506102ba565b80806102ad90610eeb565b91505061021c565b505f90505b919050565b5f81815481106102cd575f80fd5b905f5260205f2090600202015f91509050805f0180546102ec90610adb565b80601f016020809104026020016040519081016040528092919081815260200182805461031890610adb565b80156103635780601f1061033a57610100808354040283529160200191610363565b820191905f5260205f20905b81548152906001019060200180831161034657829003601f168201915b505050505090806001015f9054906101000a900460ff16905082565b61038881610590565b6103c7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103be90610dc1565b60405180910390fd5b5f5b5f805490508110156104865781805190602001205f82815481106103f0576103ef610ddf565b5b905f5260205f2090600202015f0160405161040b9190610ea8565b6040518091039020036104735760015f828154811061042d5761042c610ddf565b5b905f5260205f2090600202016001015f8282829054906101000a900460ff166104569190610f32565b92506101000a81548160ff021916908360ff160217905550610486565b808061047e90610eeb565b9150506103c9565b5050565b60605f805480602002602001604051908101604052809291908181526020015f905b82821015610587578382905f5260205f2090600202016040518060400160405290815f820180546104dc90610adb565b80601f016020809104026020016040519081016040528092919081815260200182805461050890610adb565b80156105535780601f1061052a57610100808354040283529160200191610553565b820191905f5260205f20905b81548152906001019060200180831161053657829003601f168201915b50505050508152602001600182015f9054906101000a900460ff1660ff1660ff1681525050815260200190600101906104ac565b50505050905090565b5f805f90505b5f805490508110156106025782805190602001205f82815481106105bd576105bc610ddf565b5b905f5260205f2090600202015f016040516105d89190610ea8565b6040518091039020036105ef576001915050610607565b80806105fa90610eeb565b915050610596565b505f90505b919050565b5f604051905090565b5f80fd5b5f80fd5b5f80fd5b5f80fd5b5f601f19601f8301169050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b61066b82610625565b810181811067ffffffffffffffff8211171561068a57610689610635565b5b80604052505050565b5f61069c61060c565b90506106a88282610662565b919050565b5f67ffffffffffffffff8211156106c7576106c6610635565b5b6106d082610625565b9050602081019050919050565b828183375f83830152505050565b5f6106fd6106f8846106ad565b610693565b90508281526020810184848401111561071957610718610621565b5b6107248482856106dd565b509392505050565b5f82601f8301126107405761073f61061d565b5b81356107508482602086016106eb565b91505092915050565b5f6020828403121561076e5761076d610615565b5b5f82013567ffffffffffffffff81111561078b5761078a610619565b5b6107978482850161072c565b91505092915050565b5f60ff82169050919050565b6107b5816107a0565b82525050565b5f6020820190506107ce5f8301846107ac565b92915050565b5f819050919050565b6107e6816107d4565b81146107f0575f80fd5b50565b5f81359050610801816107dd565b92915050565b5f6020828403121561081c5761081b610615565b5b5f610829848285016107f3565b91505092915050565b5f81519050919050565b5f82825260208201905092915050565b5f5b8381101561086957808201518184015260208101905061084e565b5f8484015250505050565b5f61087e82610832565b610888818561083c565b935061089881856020860161084c565b6108a181610625565b840191505092915050565b5f6040820190508181035f8301526108c48185610874565b90506108d360208301846107ac565b9392505050565b5f81519050919050565b5f82825260208201905092915050565b5f819050602082019050919050565b5f82825260208201905092915050565b5f61091d82610832565b6109278185610903565b935061093781856020860161084c565b61094081610625565b840191505092915050565b610954816107a0565b82525050565b5f604083015f8301518482035f8601526109748282610913565b9150506020830151610989602086018261094b565b508091505092915050565b5f61099f838361095a565b905092915050565b5f602082019050919050565b5f6109bd826108da565b6109c781856108e4565b9350836020820285016109d9856108f4565b805f5b85811015610a1457848403895281516109f58582610994565b9450610a00836109a7565b925060208a019950506001810190506109dc565b50829750879550505050505092915050565b5f6020820190508181035f830152610a3e81846109b3565b905092915050565b7f43616e64696461746520616c72656164792065786973747300000000000000005f82015250565b5f610a7a60188361083c565b9150610a8582610a46565b602082019050919050565b5f6020820190508181035f830152610aa781610a6e565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610af257607f821691505b602082108103610b0557610b04610aae565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610b677fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610b2c565b610b718683610b2c565b95508019841693508086168417925050509392505050565b5f819050919050565b5f610bac610ba7610ba2846107d4565b610b89565b6107d4565b9050919050565b5f819050919050565b610bc583610b92565b610bd9610bd182610bb3565b848454610b38565b825550505050565b5f90565b610bed610be1565b610bf8818484610bbc565b505050565b5b81811015610c1b57610c105f82610be5565b600181019050610bfe565b5050565b601f821115610c6057610c3181610b0b565b610c3a84610b1d565b81016020851015610c49578190505b610c5d610c5585610b1d565b830182610bfd565b50505b505050565b5f82821c905092915050565b5f610c805f1984600802610c65565b1980831691505092915050565b5f610c988383610c71565b9150826002028217905092915050565b610cb182610832565b67ffffffffffffffff811115610cca57610cc9610635565b5b610cd48254610adb565b610cdf828285610c1f565b5f60209050601f831160018114610d10575f8415610cfe578287015190505b610d088582610c8d565b865550610d6f565b601f198416610d1e86610b0b565b5f5b82811015610d4557848901518255600182019150602085019450602081019050610d20565b86831015610d625784890151610d5e601f891682610c71565b8355505b6001600288020188555050505b505050505050565b7f43616e64696461746520646f6573206e6f7420657869737400000000000000005f82015250565b5f610dab60188361083c565b9150610db682610d77565b602082019050919050565b5f6020820190508181035f830152610dd881610d9f565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52603260045260245ffd5b5f81905092915050565b5f819050815f5260205f209050919050565b5f8154610e3481610adb565b610e3e8186610e0c565b9450600182165f8114610e585760018114610e6d57610e9f565b60ff1983168652811515820286019350610e9f565b610e7685610e16565b5f5b83811015610e9757815481890152600182019150602081019050610e78565b838801955050505b50505092915050565b5f610eb38284610e28565b915081905092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610ef5826107d4565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610f2757610f26610ebe565b5b600182019050919050565b5f610f3c826107a0565b9150610f47836107a0565b9250828201905060ff811115610f6057610f5f610ebe565b5b9291505056fea2646970667358221220b289a911f77d262173971834ef7c877e74e03a53935ed134becc40bb719f6dbd64736f6c63430008140033";

    public static final String FUNC_CANDIDATELIST = "candidateList";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_VOTEFORCANDIDATE = "voteForCandidate";

    public static final String FUNC_TOTALVOTES = "totalVotes";

    public static final String FUNC_GETCANDIDATELIST = "getCandidateList";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Voting(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> candidateList(BigInteger param0) {
        final Function function = new Function(FUNC_CANDIDATELIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> addCandidate(String candidateName) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new Utf8String(candidateName)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> voteForCandidate(String candidate) {
        final Function function = new Function(
                FUNC_VOTEFORCANDIDATE, 
                Arrays.<Type>asList(new Utf8String(candidate)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> totalVotes(String candidate) {
        final Function function = new Function(FUNC_TOTALVOTES, 
                Arrays.<Type>asList(new Utf8String(candidate)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getCandidateList() {
        final Function function = new Function(FUNC_GETCANDIDATELIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Candidate>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Voting(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Voting(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Voting.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Voting.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class Candidate extends DynamicStruct {
        public String name;

        public BigInteger votes;

        public Candidate(String name, BigInteger votes) {
            super(new Utf8String(name),
                    new Uint8(votes));
            this.name = name;
            this.votes = votes;
        }

        public Candidate(Utf8String name, Uint8 votes) {
            super(name, votes);
            this.name = name.getValue();
            this.votes = votes.getValue();
        }

        public String getCandidateName() {
            return name;
        }
    }
}
