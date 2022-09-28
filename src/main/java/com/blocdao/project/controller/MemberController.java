package com.blocdao.project.controller;

import com.blocdao.project.dto.member.request.MemberSingupRequestDto;
import com.blocdao.project.dto.member.response.MemberProfileResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private String activeProfile;

    private final MemberService memberService;

    private final Environment environment;

    //회원가입
    @PostMapping()
    public ResponseEntity<String> signup(@Valid @RequestBody MemberSingupRequestDto memberSingupRequestDto,
                                                    @RequestHeader("Authorization") String header) {

        activeProfile = environment.getActiveProfiles()[0];

        if(activeProfile.equals("local")) {
            return memberService.signupMock(memberSingupRequestDto, header);
        } else {
            return memberService.signup(memberSingupRequestDto, header);
        }
    }

    // 로그인은 토큰만 확인하면 됩니다.
    @GetMapping()
    public ResponseEntity<String> login(Authentication authentication) {

        // 프론트에서 로그인 요청시 회원이 존재하지 않으면 Http 코드가 404일시 회원가입페이지로 넘어감
        // 유저가 존재하지 않을 시 404 리턴
        Member member = (Member) authentication.getPrincipal();
        return memberService.login(member);
    }

    // 회원 정보 수정
    @PutMapping()
    public ResponseEntity<String> updateMember(Authentication authentication) {

        Member member = (Member) authentication.getPrincipal();
        return memberService.signOut(member);
    }
    
    // 회원 탈퇴
    @DeleteMapping()
    public ResponseEntity<String> signOut(Authentication authentication) {
        return memberService.signOut((Member) authentication.getPrincipal());
    }

    // 마이페이지 출력용 데이터를 호출하는 api
    // 내가 작성한 모집 글 보기
    @GetMapping("/my")
    public ResponseEntity<MemberProfileResponseDto> profile(Authentication authentication) {
        // 회원 서비스에서 맴버 객체를 새롭게 생성하여 조회 기능을 수행한다.
        return memberService.profile((Member) authentication.getPrincipal());
    }
}