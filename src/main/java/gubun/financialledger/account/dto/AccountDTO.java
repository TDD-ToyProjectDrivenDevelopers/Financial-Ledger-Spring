package gubun.financialledger.account.dto;

import gubun.financialledger.account.entity.Account;
import gubun.financialledger.bank.entity.Bank;
import gubun.financialledger.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
public class AccountDTO {

    @NotBlank(message="Write your Account Name")
    private String accountName;

    @PositiveOrZero(message="There's no money")
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
