package gubun.financialledger.account;

import gubun.financialledger.bank.Bank;
import gubun.financialledger.common.BaseEntity;
import gubun.financialledger.user.User;

import javax.persistence.*;

@Entity
public class Account extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "deposit_amount")
    private Long depositAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;
}
