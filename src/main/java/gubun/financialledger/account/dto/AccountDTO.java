package gubun.financialledger.account.dto;

import gubun.financialledger.account.entity.Account;
import gubun.financialledger.bank.entity.Bank;
import gubun.financialledger.user.entity.User;

import javax.persistence.*;

public class AccountDTO {
    private String accountName;

    private Long depositAmount;

    private User user;

    private Bank bank;

    public Account toEntity(){
        return Account.builder()
                .accountName(accountName)
                .depositAmount(depositAmount)
                .user(user)
                .bank(bank)
                .build();
    }
}
