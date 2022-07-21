package gubun.financialledger.account.repository;

import gubun.financialledger.account.entity.Account;
import gubun.financialledger.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select b.bankName from Account a inner join a.bank b")
    List<String> callBankList();
}
