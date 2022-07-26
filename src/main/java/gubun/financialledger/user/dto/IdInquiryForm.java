package gubun.financialledger.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class IdInquiryForm {

    @NotBlank(message = "이메일을 작성해주세요.")
    @Email(message = "이메일 형식으로 작성해주세요.")
    private String email;
}
