package gubun.financialledger.bank.dto;

import gubun.financialledger.bank.entity.Bank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDTO {
    private String bankName;

    public Bank toEntity(){
        return Bank.builder()
                .bankName(bankName)
                .build();
    }
}
