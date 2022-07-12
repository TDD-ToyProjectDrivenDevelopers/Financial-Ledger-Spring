package gubun.financialledger.user;

import gubun.financialledger.user.dto.RegistrationForm;
import gubun.financialledger.user.entity.User;
import gubun.financialledger.user.repository.UserRepository;
import gubun.financialledger.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static gubun.financialledger.user.entity.UserRole.USER;

@Component
@RequiredArgsConstructor
public class TestUser {

    private final UserService userService;

    @PostConstruct
    public void saveTestUser(){

        RegistrationForm form = new RegistrationForm();
        form.setUsername("user");
        form.setPassword("1234");
        form.setRepeatPassword("1234");
        form.setEmail("1234@1234.com");

        if(!userService.isDuplicatedUser(form.getUsername()))
            userService.save(form);
    }
}
