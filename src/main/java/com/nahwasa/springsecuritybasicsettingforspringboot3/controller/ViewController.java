package com.nahwasa.springsecuritybasicsettingforspringboot3.controller;

import com.nahwasa.springsecuritybasicsettingforspringboot3.configuration.AdminAuthorize;
import com.nahwasa.springsecuritybasicsettingforspringboot3.configuration.UserAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }
    /**
     * @AuthenticationPrincipal : 유저 정보를 백엔드에서 획득 가능하게 해줌. (로그인 정보 출력) 뒤에는 userDetail 을 붙혀줘야 한다.
     * 추가 공부 필요
     **/
    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("loginId", user.getUsername());
        model.addAttribute("loginRoles", user.getAuthorities());
        return "dashboard";
    }
    /**
     * @PreAuthorize("hasAnyRole('ADMIN')") : 해당 어노테이션을 사용, 특정 역할을 지정해주면, 역할에 맞는 사용자만이 VIEW 에 접근 가능해진다.
     * 일일히 역할 검증이 필요한 부분에 상단의 코드를 넣어주어도 상관은 없지만, 가독성을 위해 커스텀 어노테이션으로 만들었다.
     * configuration -> Admin , User
     * */
    @GetMapping("/setting/admin")
    @AdminAuthorize
    public String adminSettingPage() {
        return "admin_setting";
    }

    @GetMapping("/setting/user")
    @UserAuthorize
    public String userSettingPage() {
        return "user_setting";
    }
}