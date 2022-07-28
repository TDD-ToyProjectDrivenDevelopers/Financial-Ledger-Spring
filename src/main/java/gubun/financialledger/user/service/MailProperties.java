package gubun.financialledger.user.service;

public interface MailProperties {
    String SERVICE_NAME = "Gubun TDD";
    String AUTH_SUBJECT = "[Gubun TDD 회원가입 서비스 이메일 인증 입니다.]";
    String ID_INQUIRY_SUBJECT = "[Gubun TDD 아이디 찾기 서비스 입니다.]";
    String AUTH_HEADER = "<h1>Gubun TDD 가입 메일인증 입니다</h1>";
    String ID_INQUIRY_HEADER = "<h1>Gubun TDD 아이디 입니다</h1>";
    String AUTH_SENDER = "gubun@gmail.com";
}
