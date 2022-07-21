package gubun.financialledger.account.service;

import gubun.financialledger.account.entity.Account;
import gubun.financialledger.account.repository.AccountRepository;
import gubun.financialledger.bank.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    public void saveAccount(Account account){
        accountRepo.save(account);
    }
}
