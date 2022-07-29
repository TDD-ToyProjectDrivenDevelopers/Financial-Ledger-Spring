package gubun.financialledger.user.controller;

import gubun.financialledger.user.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/auth")
    public String emailAuth(String receiver) throws MessagingException, UnsupportedEncodingException {
        log.info("회원가입 인증 메일 전송={}", receiver);
        return mailService.sendAccessCode(receiver);
    }
}
