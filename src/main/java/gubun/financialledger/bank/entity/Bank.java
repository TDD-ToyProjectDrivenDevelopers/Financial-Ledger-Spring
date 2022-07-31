package gubun.financialledger.bank.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Bank {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_id")
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @Builder
    public Bank(String bankName){
        this.bankName = bankName;
    }
}
