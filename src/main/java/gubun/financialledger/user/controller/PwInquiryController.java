package gubun.financialledger.user.controller;

import gubun.financialledger.user.dto.PwInquiryForm;
import gubun.financialledger.user.entity.User;
import gubun.financialledger.user.service.MailService;
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

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/pwInquiry")
@RequiredArgsConstructor
public class PwInquiryController {

    private final UserService userService;

    @GetMapping
    public String pwInquiryForm(@ModelAttribute("form") PwInquiryForm form){
        return "pwInquiry";
    }

    @PostMapping
    public String pwInquiry(@Validated @ModelAttribute("form") PwInquiryForm form, BindingResult bindingResult){
        //회원 일치 검증
        Optional<User> user = userService.isValidatedUser(form.getUsername(), form.getEmail());
        if(user.isEmpty()){
            log.info("회원일치 오류");
            bindingResult.reject("notInvalidUser", "해당 회원은 존재하지 않습니다.");
            return "pwInquiry";
        }

        // 비밀번호 일치 검증
        if(!form.getPassword().equals(form.getRepeatPassword())){
            bindingResult.reject("notSamePassword", "비밀번호가 일치하지 않습니다");
            return "register";
        }

        //기타 필드 검증
        if(bindingResult.hasErrors()){
            log.info("error={}", bindingResult);
            return "pwInquiry";
        }

        userService.updatePassword(user.get(), form.getPassword());

        return "redirect:/login";
    }
}
