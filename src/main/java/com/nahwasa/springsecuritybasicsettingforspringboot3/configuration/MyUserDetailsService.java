package com.nahwasa.springsecuritybasicsettingforspringboot3.configuration;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Member;
import com.nahwasa.springsecuritybasicsettingforspringboot3.service.MemberService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    /**
     * UserDetailsService: 스프링 시큐리티에서 사용자 정보를 조회하는 인터페이스
     * [override] loadUserByUsername(string username) : 주어진 사용자 이름을 기반으로 사용자를 조회. (사용자 이름 -> view 에서 넘어온 사용자 이름)
     *              조회 완료시 -> UserDetail 객체를 리턴해야하며, User.build() 로 해당 객체를 생성할수 있다. (마지막 코드가 userDetail 임)
     *              *재정의 이기 때문에, 결국 조회 로직은 내가 구현해야 함*
     * */
    private final MemberService memberService;

    public MyUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * String insertedUserId -> 로그인 시, 내가 입력한 아이디값이 들어있음
     * 스프링 시큐리티는 로그인 폼에서 전달된 입력값을 자동으로 수집하기 때문에 가능함
     * */
    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<Member> findOne = memberService.findOne(insertedUserId);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));

        return User.builder() //예외 없을시 (회원이 있다는 것) db 에서 정보 조회 (userDetail)
                .username(member.getUserid())
                .password(member.getPw())
                .roles(member.getRoles())
                .build();
    }
}