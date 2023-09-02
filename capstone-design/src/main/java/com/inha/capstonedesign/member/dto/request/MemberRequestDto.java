package com.inha.capstonedesign.member.dto.request;

import com.inha.capstonedesign.member.entity.Address;
import com.inha.capstonedesign.member.entity.Gender;
import com.inha.capstonedesign.member.entity.Member;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberRequestDto {

    @Getter
    public static class SignUp {


        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private String memberEmail;
        @NotNull
        private String memberPassword;
        @NotNull
        private String memberName;
        @NotNull
        private String memberNickName;
        @NotNull
        private LocalDate memberBirthDate;
        @NotNull
        private String memberGender;
        @NotNull
        private String memberAddress;

        public Gender setEnumMemberGender(String gender) {
            if (gender.equals(Gender.MALE.getKorean())) {
                return Gender.MALE;
            } else {
                return Gender.FEMALE;
            }
        }

        public Address setEnumMemberAddress(String address) {
            if (address.equals(Address.GYEONGGI.getKorean())) {
                return Address.GYEONGGI;
            } else if (address.equals(Address.GANGWON.getKorean())) {
                return Address.GANGWON;
            } else if (address.equals(Address.CHUNGCHEONGBUK.getKorean())) {
                return Address.CHUNGCHEONGBUK;
            } else if (address.equals(Address.CHUNGCHEONGNAM.getKorean())) {
                return Address.CHUNGCHEONGNAM;
            } else if (address.equals(Address.JEOLLABUK.getKorean())) {
                return Address.JEOLLABUK;
            } else if (address.equals(Address.JEOLLANAM.getKorean())) {
                return Address.JEOLLANAM;
            } else if (address.equals(Address.GYEONGSANGBUK.getKorean())) {
                return Address.GYEONGSANGBUK;
            } else if (address.equals(Address.GYEONGSANGNAM.getKorean())) {
                return Address.GYEONGSANGNAM;
            } else {
                return Address.SEOUL;
            }
        }

        public Member toEntity() {
            return Member.builder()
                    .memberEmail(memberEmail)
                    .memberPassword(memberPassword)
                    .memberName(memberName)
                    .memberNickName(memberNickName)
                    .memberBirthDate(memberBirthDate)
                    .memberGender(setEnumMemberGender(memberGender))
                    .memberAddress(setEnumMemberAddress(memberAddress))
                    .build();
        }
    }

    @Getter
    public static class Login {


        //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private String memberEmail;

        private String memberPassword;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(memberEmail, memberPassword);
        }
    }

    @Getter
    public static class Access {
        private String email;
        private List<String> roles = new ArrayList<>();

        private Access(String email, List<String> authorities) {
            this.email = email;
            roles.addAll(authorities);
        }

        public static Access from(String email, List<String> authorities) {
            return new Access(email, authorities);
        }
    }
}
