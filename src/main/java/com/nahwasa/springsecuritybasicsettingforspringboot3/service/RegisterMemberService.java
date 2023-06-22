package com.nahwasa.springsecuritybasicsettingforspringboot3.service;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Member;
import com.nahwasa.springsecuritybasicsettingforspringboot3.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterMemberService {
    private final MemberRepository repository;

    @Autowired
    public RegisterMemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(String userid, String pw) {

        Member member = Member.createUser(userid, pw);
        log.info("{}",member);
        validateDuplicateMember(member);
        repository.save(member);
        log.info("{}",member.getId());
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        log.info("{}",member);
        repository.findByUserid(member.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
