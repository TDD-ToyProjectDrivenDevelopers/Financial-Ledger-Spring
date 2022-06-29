package gubun.financialledger.txhistory;

import gubun.financialledger.account.Account;
import gubun.financialledger.common.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TXHistory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tx_history_id")
    private Long id;

    @Column(name = "tx_type")
    @Enumerated(EnumType.STRING)
    private TXType txType;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "description")
    private String description;

    // TODO : 어떤 타입으로 지정해야 하는지 피드백 부탁합니다
    @Column(name = "tx_date")
    private Date tx_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accont_id")
    private Account account;
}
