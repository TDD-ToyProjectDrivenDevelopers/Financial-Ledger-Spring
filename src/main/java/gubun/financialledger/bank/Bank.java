package gubun.financialledger.bank;

import gubun.financialledger.common.BaseEntity;

import javax.persistence.*;

@Entity
public class Bank {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_id")
    private Long id;

    @Column(name = "bank_name")
    private String bankName;
}
