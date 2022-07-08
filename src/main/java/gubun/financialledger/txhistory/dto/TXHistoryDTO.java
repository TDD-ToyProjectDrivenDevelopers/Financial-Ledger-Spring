package gubun.financialledger.txhistory.dto;

import gubun.financialledger.account.entity.Account;
import gubun.financialledger.txhistory.entity.TXHistory;
import gubun.financialledger.txhistory.type.TXType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
public class TXHistoryDTO {

    @NotBlank(message = "Type을 설정해주세요.")
    private TXType txType;

    @Positive
    private Long amount;

    private LocalDateTime tx_date; //입력시 html단에서 조정 input type="date"

    private Account account;

    public TXHistory toEntity(){
        return TXHistory.builder()
                .txType(txType)
                .amount(amount)
                .tx_date(tx_date)
                .account(account)
                .build();
    }
}
