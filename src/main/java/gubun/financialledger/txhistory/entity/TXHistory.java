package gubun.financialledger.txhistory.entity;

import gubun.financialledger.account.entity.Account;
import gubun.financialledger.txhistory.type.TXType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
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

    @Builder
    public TXHistory(TXType txType, Long amount, LocalDateTime tx_date, Account account){
        this.txType = txType;
        this.amount = amount;
        this.tx_date = tx_date;
        this.account = account;
    }
}
