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

    private final MailHandler mailHandler;

    public String sendAccessCode(String receiver) throws MessagingException, UnsupportedEncodingException {
        mailHandler.setSubject(MailProperties.AUTH_SUBJECT);
        String authKey = createAuthKey();
        mailHandler.setText(
                new StringBuilder()
                        .append(MailProperties.AUTH_HEADER)
                        .append("<h2>인증 번호 : ")
                        .append(authKey)
                        .append("</h2>").toString()
        );
        mailHandler.setFrom(MailProperties.AUTH_SENDER, MailProperties.SERVICE_NAME);
        mailHandler.setTo(receiver);
        mailHandler.send();

        return authKey;
    }

    public void sendUsername(String receiver, String username) throws MessagingException, UnsupportedEncodingException {
        mailHandler.setSubject(MailProperties.ID_INQUIRY_SUBJECT);
        mailHandler.setText(
                new StringBuilder()
                        .append(MailProperties.ID_INQUIRY_HEADER)
                        .append("<h2>아이디 : ")
                        .append(username)
                        .append("</h2>").toString()
        );
        mailHandler.setFrom(MailProperties.AUTH_SENDER, MailProperties.SERVICE_NAME);
        mailHandler.setTo(receiver);
        mailHandler.send();
    }

    private String createAuthKey(){
        return new RandomString(8, new Random()).nextString();
    }
}
