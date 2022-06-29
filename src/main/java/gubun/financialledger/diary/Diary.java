package gubun.financialledger.diary;

import gubun.financialledger.common.BaseEntity;
import gubun.financialledger.txhistory.TXHistory;

import javax.persistence.*;

@Entity
public class Diary extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diary_id")
    private Long id;

    @Column(name = "diary_title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tx_history_id")
    private TXHistory txHistory;
}
