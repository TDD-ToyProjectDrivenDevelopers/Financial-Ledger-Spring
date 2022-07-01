package gubun.financialledger.txhistory;

import gubun.financialledger.account.Account;
import gubun.financialledger.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TXHistory {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tx_history_id")
    private Long id;

    @Column(name = "tx_type")
    @Enumerated(EnumType.STRING)
    private TXType txType;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "tx_date")
    private LocalDateTime tx_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accont_id")
    private Account account;
}
