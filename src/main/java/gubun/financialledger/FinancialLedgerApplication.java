package gubun.financialledger;

import gubun.financialledger.user.dto.RegistrationForm;
import gubun.financialledger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@SpringBootApplication
public class FinancialLedgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialLedgerApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@PostConstruct
	public void initDefaultUser(){
		RegistrationForm form = new RegistrationForm();
		form.setUsername("user");
		form.setPassword("1234");
		form.setRepeatPassword("1234");
		form.setEmail("1234@1234.com");

		if(!userService.isDuplicatedUser(form.getUsername()))
			userService.save(form);
	}
}
