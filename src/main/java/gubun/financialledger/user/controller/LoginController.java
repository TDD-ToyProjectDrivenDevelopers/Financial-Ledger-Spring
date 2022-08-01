package gubun.financialledger.user.controller;

import gubun.financialledger.user.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    // 메인페이지 로그인한 유저 정보 전달

    @GetMapping("/")
    public ModelAndView currentUserName(@AuthenticationPrincipal PrincipalDetails user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("user", user);
        return mv;
    }
}
