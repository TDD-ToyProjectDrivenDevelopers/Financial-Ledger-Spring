package gubun.financialledger.user.controller;

import gubun.financialledger.user.dto.RegistrationForm;
import gubun.financialledger.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String registerForm(@ModelAttribute("user") RegistrationForm form){
        return "register";
    }

    @PostMapping
    public String register(@Validated @ModelAttribute("user") RegistrationForm form, BindingResult bindingResult){

        // 중복 회원 검증
        if(userService.isDuplicatedUser(form.getUsername())){
            log.info("중복회원 오류 username={}", form.getUsername());
            bindingResult.rejectValue("username", "duplicateUser", "아이디가 중복됩니다");
            return "register";
        }

        // 비밀번호 일치 검증
        if(!form.getPassword().equals(form.getRepeatPassword())){
            bindingResult.reject("notSamePassword", "비밀번호가 일치하지 않습니다");
            return "register";
        }

        // 그 외 기타 필드 검증
        if(bindingResult.hasErrors()){
            log.info("error={}", bindingResult);
            return "register";
        }

        userService.save(form);
        return "redirect:/";
    }
}
