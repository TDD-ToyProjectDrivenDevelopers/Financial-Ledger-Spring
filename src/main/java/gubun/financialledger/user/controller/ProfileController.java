package gubun.financialledger.user.controller;

import gubun.financialledger.user.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    @GetMapping("/profile")
    public ModelAndView currentUser(@AuthenticationPrincipal PrincipalDetails user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("profile");
        mv.addObject("user", user);
        return mv;
    }
}
