package gubun.financialledger.user.service;

import gubun.financialledger.user.auth.MailHandler;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final String SUBJECT = "[Gubun TDD 회원가입 서비스 이메일 인증 입니다.]";
    private static final String SENDER = "gubun@gmail.com";

    private final MailHandler mailHandler;

    public String sendAccessCode(String receiver) throws MessagingException, UnsupportedEncodingException {
        mailHandler.setSubject(SUBJECT);
        String authKey = createAuthKey();
        mailHandler.setText(
                new StringBuilder()
                        .append("<h1>Gubun TDD 가입 메일인증 입니다</h1>")
                        .append("<h2>인증 번호 : ")
                        .append(authKey)
                        .append("</h2>").toString()
        );
        mailHandler.setFrom(SENDER, "Gubun TDD");
        mailHandler.setTo(receiver);
        mailHandler.send();

        return authKey;
    }

    private String createAuthKey(){
        return new RandomString(8, new Random()).nextString();
    }
}
