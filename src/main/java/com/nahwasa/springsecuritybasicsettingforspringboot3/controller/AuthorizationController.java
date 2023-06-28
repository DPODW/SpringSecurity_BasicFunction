package com.nahwasa.springsecuritybasicsettingforspringboot3.controller;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Member;
import com.nahwasa.springsecuritybasicsettingforspringboot3.dto.MemberJoinDto;
import com.nahwasa.springsecuritybasicsettingforspringboot3.service.RegisterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final RegisterMemberService registerMemberService;

    public AuthorizationController(RegisterMemberService registerMemberService) {
        this.registerMemberService = registerMemberService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinDto dto) {
        try {
            registerMemberService.join(dto.getUserid(), dto.getPw());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/SAVE")
    public ResponseEntity<String> save(@RequestBody Map<String,String> requestBody){
        String userid = requestBody.get("userid");
        String pw = requestBody.get("pw");
        log.info("{}",userid);
        log.info("{}",pw);

        registerMemberService.join(userid,pw);
        return ResponseEntity.ok("ok");
    }

}
