package gubun.financialledger;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
//@EnableEncryptableProperties
@SpringBootApplication
public class FinancialLedgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialLedgerApplication.class, args);
	}

}
