package com.inha.capstonedesign.member.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_email")
    @NotNull
    private String memberEmail;

    @Column(name = "member_password")
    @NotNull
    private String memberPassword;

    @Column(name = "member_name")
    @NotNull
    private String memberName;

    @Column(name = "member_nickname")
    @NotNull
    private String memberNickName;

    @Column(name = "member_birth")
    @NotNull
    private LocalDate memberBirthDate;

    @Column(name = "member_age")
    @NotNull
    private Integer memberAge;

    @Column(name = "member_gender")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender memberGender;

    @Column(name = "member_Address")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Address memberAddress;

    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<UserRole> roles = new ArrayList<>();

    @Builder
    public Member(String memberEmail, String memberPassword, String memberName, String memberNickName, LocalDate memberBirthDate, Gender memberGender, Address memberAddress) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.memberBirthDate = memberBirthDate;

        LocalDate currentDate = LocalDate.now();
        this.memberAge = Period.between(memberBirthDate, currentDate).getYears();

        this.memberGender = memberGender;
        this.memberAddress = memberAddress;
        this.roles.add(UserRole.ROLE_USER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    //얘는 나중에 Authentication 가져올때 이거 사용하는지 확인해보자
    @Override
    public String getUsername() {
        return memberEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
