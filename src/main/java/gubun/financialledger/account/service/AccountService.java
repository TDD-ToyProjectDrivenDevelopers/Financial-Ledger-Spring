package gubun.financialledger.account.service;

import gubun.financialledger.account.entity.Account;
import gubun.financialledger.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}
