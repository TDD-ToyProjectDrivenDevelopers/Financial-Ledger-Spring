package gubun.financialledger.txhistory.repository;

import gubun.financialledger.txhistory.entity.TXHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TXHistoryRepository extends JpaRepository<TXHistory, Long> {
}
