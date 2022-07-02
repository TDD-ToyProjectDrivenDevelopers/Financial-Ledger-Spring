package gubun.financialledger.common.entity;

import gubun.financialledger.category.entity.Category;
import gubun.financialledger.txhistory.entity.TXHistory;

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
