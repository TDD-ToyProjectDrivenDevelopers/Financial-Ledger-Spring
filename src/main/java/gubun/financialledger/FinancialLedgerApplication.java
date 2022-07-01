package gubun.financialledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FinancialLedgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialLedgerApplication.class, args);
	}

}
