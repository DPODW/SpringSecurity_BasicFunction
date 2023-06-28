package com.nahwasa.springsecuritybasicsettingforspringboot3.domain;


import lombok.Data;

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

    public static Member createUser(String userId, String pw) {
        return new Member(null, userId, pw, "USER");
    }

}
