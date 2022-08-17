package gubun.financialledger.user.controller;

import gubun.financialledger.user.auth.PrincipalDetails;
import gubun.financialledger.user.dto.IdInquiryForm;
import gubun.financialledger.user.dto.UserUpdateForm;
import gubun.financialledger.user.entity.User;
import gubun.financialledger.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public ModelAndView currentUser(@AuthenticationPrincipal PrincipalDetails user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("profile");
        mv.addObject("user", user);
        return mv;
    }

    @ResponseBody
    @PutMapping("/users")
    public String userUpdate(
            @AuthenticationPrincipal PrincipalDetails user,
            @Validated @ModelAttribute("form") UserUpdateForm form, BindingResult bindingResult
    ) {
        //구현중
        return "success";
    }

    @ResponseBody
    @DeleteMapping("/users")
    public String deleteUser(
            @RequestBody String username) {
        userService.deleteUser(username);
        return "redirect:/";
    }
}
