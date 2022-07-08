package gubun.financialledger.bank.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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
