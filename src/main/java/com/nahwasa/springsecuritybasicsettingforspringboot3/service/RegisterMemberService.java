package com.nahwasa.springsecuritybasicsettingforspringboot3.service;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Member;
import com.nahwasa.springsecuritybasicsettingforspringboot3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegisterMemberService {
    private final MemberRepository repository;

    private final PasswordEncoder passwordEncoder;

    public Long join(String userid, String pw) {
        Member member = Member.createUser(userid, pw, passwordEncoder);
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByUserid(member.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
