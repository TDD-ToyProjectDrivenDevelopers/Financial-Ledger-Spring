package gubun.financialledger.user.controller;

import gubun.financialledger.user.dto.RegistrationForm;
import gubun.financialledger.user.service.MailService;
import gubun.financialledger.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    private static final String REGISTER_LOGICAL_PATH = "register";

    @GetMapping
    public String registerForm(@ModelAttribute("user") RegistrationForm form){
        return REGISTER_LOGICAL_PATH;
    }

    @PostMapping
    public String register(@Validated @ModelAttribute("user") RegistrationForm form, BindingResult bindingResult){

        // 중복 회원 검증
        if(userService.isDuplicatedUser(form.getUsername())){
            log.info("중복회원 오류 username={}", form.getUsername());
            bindingResult.rejectValue("username", "duplicateUser", "아이디가 중복됩니다");
            return REGISTER_LOGICAL_PATH;
        }

        // 비밀번호 일치 검증
        if(!form.getPassword().equals(form.getRepeatPassword())){
            log.info("비밀번호 일치 오류={}", form.getPassword() + "!=" + form.getRepeatPassword());
            bindingResult.reject("notSamePassword", "비밀번호가 일치하지 않습니다");
            return REGISTER_LOGICAL_PATH;
        }

        // 그 외 기타 필드 검증
        if(bindingResult.hasErrors()){
            log.info("필드 검증 오류={}", bindingResult);
            return REGISTER_LOGICAL_PATH;
        }

        userService.save(form);
        return "redirect:/";
    }
}
