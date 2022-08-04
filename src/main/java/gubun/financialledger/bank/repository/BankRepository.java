package gubun.financialledger.bank.repository;

import gubun.financialledger.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
