package com.nahwasa.springsecuritybasicsettingforspringboot3.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePasswordEncoder implements PasswordEncoder {

    /**
     * 해당 CLASS 는 이해를 돕기 위한 암호화 인코딩 클래스.
     * 실제론 개인이 암호화를 구현해서 사용하지 않고, 잘 만들어진 라이브러리를 사용한다.
     * */

    /**
     * encode: 해당 암호화 방식(현재는 아무런 코드가 정의 X)으로 암호화한 문자열을 리턴해줍니다. -> 회원가입시, 비밀번호를 db 에 넣기 전에, 이걸 호출해서 암호화해서 넣으면 된다.
     * matches: rawPassword -> 사용자가 입력한 비밀번호 (암호화 x) / encodedPassword -> encode 에 의해서 이미 암호화 된 비밀번호
     **/
    @Override
    public String encode(CharSequence rawPassword) {
        //원래라면 여기에 암호화 로직이 들어가야 겠지. . .?
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}

    /**
     * 1. encode 로 비밀번호를 암호화 해서 DB 에 저장한다.
     * 2. rawPassword 에 사용자가 입력한 비밀번호가 들어오면, DB 에 암호화 되어 있는 비밀번호와 동일한게 있는지 검사한다.
     **/