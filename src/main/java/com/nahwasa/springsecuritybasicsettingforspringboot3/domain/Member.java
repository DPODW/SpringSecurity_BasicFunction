package com.nahwasa.springsecuritybasicsettingforspringboot3.domain;


import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class Member {

    private Long id;

    private String userid;

    private String pw;

    private String roles;

    private Member(Long id, String userid, String pw, String roleUser) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
        this.roles = roleUser;
    }

    protected Member() {}

    /**
     * Member 를 리턴할때, 비밀번호는 암호화해서 리턴하도록 함
     * */
    public static Member createUser(String userId, String pw, PasswordEncoder passwordEncoder) {
        return new Member(null, userId, passwordEncoder.encode(pw), "USER");
    }

}
