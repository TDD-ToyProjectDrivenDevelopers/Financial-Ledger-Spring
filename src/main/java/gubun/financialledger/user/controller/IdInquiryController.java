package gubun.financialledger.user.controller;

import gubun.financialledger.user.dto.IdInquiryForm;
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

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/idInquiry")
@RequiredArgsConstructor
public class IdInquiryController {

    private final UserService userService;
    private final MailService mailService;

    @GetMapping
    public String idInquiryForm(@ModelAttribute("form") IdInquiryForm form){
        return "idInquiry";
    }

    @PostMapping
    public String idInquiry(@Validated @ModelAttribute("form") IdInquiryForm form, BindingResult bindingResult) throws MessagingException, UnsupportedEncodingException {
        //이메일 일치 검증
        Optional<User> user = userService.isValidatedUser(form.getEmail());
        if(user.isEmpty()){
            log.info("유효하지 않은 이메일");
            bindingResult.reject("notInvalidEmail", "해당 이메일로 가입한 회원은 없습니다.");
            return "idInquiry";
        }

        //기타 필드 검증
        if(bindingResult.hasErrors()){
            log.info("error={}", bindingResult);
            return "idInquiry";
        }

        mailService.sendUsername(form.getEmail(), user.get().getUsername());

        return "redirect:/";
    }
}
