package gubun.financialledger.account.entity;

import gubun.financialledger.bank.entity.Bank;
import gubun.financialledger.user.entity.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "deposit_amount")
    private Long depositAmount;

    //user와 양방향향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //bank와 단방향
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Builder
    public Account(String accountName, Long depositAmount, User user, Bank bank){
        this.accountName = accountName;
        this.depositAmount = depositAmount;
        this.user = user;
        this.bank = bank;
    }
}
