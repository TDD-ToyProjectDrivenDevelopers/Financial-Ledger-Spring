package gubun.financialledger.common;

import gubun.financialledger.category.Category;
import gubun.financialledger.txhistory.TXHistory;

import javax.persistence.*;

@Entity
public class TXHistoryCategory {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tx_history_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tx_history_id")
    private TXHistory txHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
