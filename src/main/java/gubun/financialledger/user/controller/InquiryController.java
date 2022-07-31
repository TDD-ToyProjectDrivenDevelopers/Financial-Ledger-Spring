package gubun.financialledger.user.controller;

import gubun.financialledger.user.dto.IdInquiryForm;
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

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final UserService userService;
    private final MailService mailService;

    private static final String ID_INQUIRY_LOGICAL_PATH = "idInquiry";
    private static final String PW_INQUIRY_LOGICAL_PATH = "pwInquiry";

    @GetMapping("/id")
    public String idInquiryForm(@ModelAttribute("form") IdInquiryForm form){
        return ID_INQUIRY_LOGICAL_PATH;
    }

    @PostMapping("/id")
    public String idInquiry(@Validated @ModelAttribute("form") IdInquiryForm form, BindingResult bindingResult) throws MessagingException, UnsupportedEncodingException {
        //이메일 일치 검증
        Optional<User> user = userService.isValidatedUser(form.getEmail());
        if(user.isEmpty()){
            log.info("유효하지 않은 이메일={}", form.getEmail());
            bindingResult.reject("notInvalidEmail", "해당 이메일로 가입한 회원은 없습니다.");
            return ID_INQUIRY_LOGICAL_PATH;
        }
        if (hasValidationError(bindingResult)) return ID_INQUIRY_LOGICAL_PATH;

        mailService.sendUsername(form.getEmail(), user.get().getUsername());

        return "redirect:/login";
    }

    @GetMapping("/pw")
    public String pwInquiryForm(@ModelAttribute("form") PwInquiryForm form){
        return PW_INQUIRY_LOGICAL_PATH;
    }

    @PostMapping("/pw")
    public String pwInquiry(@Validated @ModelAttribute("form") PwInquiryForm form, BindingResult bindingResult){
        //회원 일치 검증
        Optional<User> user = userService.isValidatedUser(form.getUsername(), form.getEmail());
        if(user.isEmpty()){
            log.info("유효하지 않은 회원={}", form.getUsername());
            bindingResult.reject("notInvalidUser", "해당 회원은 존재하지 않습니다.");
            return PW_INQUIRY_LOGICAL_PATH;
        }

        // 비밀번호 일치 검증
        if(!form.getPassword().equals(form.getRepeatPassword())){
            log.info("비밀번호 일치 오류={}", form.getPassword() + "!=" + form.getRepeatPassword());
            bindingResult.reject("notSamePassword", "비밀번호가 일치하지 않습니다");
            return PW_INQUIRY_LOGICAL_PATH;
        }

        //기타 필드 검증
        if (hasValidationError(bindingResult)) return PW_INQUIRY_LOGICAL_PATH;

        userService.updatePassword(user.get(), form.getPassword());

        return "redirect:/login";
    }

    private boolean hasValidationError(BindingResult bindingResult) {
        //기타 필드 검증
        if (bindingResult.hasErrors()) {
            log.info("필드 검증 오류={}", bindingResult);
            return true;
        }
        return false;
    }
}
