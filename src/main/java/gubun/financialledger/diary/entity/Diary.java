package gubun.financialledger.diary.entity;

import gubun.financialledger.common.entity.BaseEntity;
import gubun.financialledger.txhistory.entity.TXHistory;

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

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tx_history_id")
    private TXHistory txHistory;
}
