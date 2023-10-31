package com.inha.capstonedesign.admin.controller;

import com.inha.capstonedesign.auth.service.AuthService;
import com.inha.capstonedesign.global.response.ErrorResponse;
import com.inha.capstonedesign.member.dto.response.MemberResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admins", description = "관리자의 회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/members")
public class AdminMemberController {

    private final AuthService authService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "관리자 권한 부여(임시 api)", description = "PathVariable로 받은 회원에게 관리자 권한 부여 \n지금은 임시 api로 이메일을 받지만 나중에 프론트 다 구현시 id로 받을 예정")
    @Parameters({
            @Parameter(name = "memberEmail", example = "test@gmail.com")
    })
    @PostMapping("/authority/{memberEmail}")
    public ResponseEntity<String> upgradeAuthority(@PathVariable String memberEmail) {
        authService.upgradeAuthority(memberEmail);

        return ResponseEntity.ok(null);
    }
}
