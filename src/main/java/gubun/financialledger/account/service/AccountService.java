package gubun.financialledger.account.service;

import gubun.financialledger.account.dto.AccountDTO;
import gubun.financialledger.account.entity.Account;
import gubun.financialledger.account.repository.AccountRepository;
import gubun.financialledger.bank.entity.Bank;
import gubun.financialledger.user.dto.RegistrationForm;
import gubun.financialledger.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void saveAccount(AccountDTO accountDto, User user){
        accountRepository.save(accountDto.toEntity(user));
    }

    public Page<Account> getAccountPage(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "id"));
        return accountRepository.findAll(pageable);
    }
}
