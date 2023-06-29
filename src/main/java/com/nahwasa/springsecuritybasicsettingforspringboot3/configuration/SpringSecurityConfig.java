package com.nahwasa.springsecuritybasicsettingforspringboot3.configuration;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {
    /**
     * 시큐리티 설정 CLASS
     * */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/status", "/images/**", "/view/join", "/auth/join").permitAll() //인증 되어선 안될 페이지
                        .anyRequest().authenticated() // 어떠한 요청이라도 인증필요
                )
                .formLogin(login -> login
                        .loginPage("/view/login")	// [A] 커스텀 로그인 페이지 지정 (스프링 기본 제공 페이지가 아닌, 임의로 만든 커스텀 페이지)
                        .loginProcessingUrl("/login-process")	// [B] submit 받을 url

                        .usernameParameter("userid")	// [C] submit 할 아이디 C , D 는 HTML 의 NAME 속성을 뜻함 (VIEW 에서 값을 받는다는 뜻)
                        .passwordParameter("pw")	// [D] submit 할 비밀번호

                        .defaultSuccessUrl("/view/dashboard", true) //성공시 대쉬보드로 (VIEW)
                        .permitAll()// 대시보드 이동이 막히면 안되므로 얘는 허용
                )
                .logout(withDefaults()); // 로그아웃은 기본설정으로 (/logout으로 인증해제)
        return http.build();
    }
}
