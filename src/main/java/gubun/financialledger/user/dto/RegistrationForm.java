package gubun.financialledger.user.dto;

import gubun.financialledger.user.entity.User;
import gubun.financialledger.user.entity.UserRole;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationForm {

    @NotBlank(message = "아이디를 작성해주세요.")
    @Pattern(
            regexp = "^[A-Za-z0-9]{6,18}$",
            message = "아이디는 숫자, 문자 포함의 6~18자 이내로 작성해주세요."
    )
    private String username;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    @Pattern(
            regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
            message = "비밀번호는 숫자, 문자, 특수문자 포함의 8~15자 이내로 작성해주세요."
    )
    private String password;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    @Pattern(
            regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
            message = "비밀번호는 숫자, 문자, 특수문자 포함의 8~15자 이내로 작성해주세요."
    )
    private String repeatPassword;

    @NotBlank(message = "이메일을 작성해주세요.")
    @Email(message = "이메일 형식으로 작성해주세요.")
    private String email;

    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .role(UserRole.USER)
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .isDeleted(false)
                .build();
    }
}
