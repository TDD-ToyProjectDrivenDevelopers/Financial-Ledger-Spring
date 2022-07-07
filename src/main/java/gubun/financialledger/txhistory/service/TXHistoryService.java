package gubun.financialledger.txhistory.service;

import gubun.financialledger.txhistory.entity.TXHistory;
import gubun.financialledger.txhistory.repository.TXHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TXHistoryService {

    @Autowired
    private TXHistoryRepository txRepo;

    public List<TXHistory> findAll() {
        List<TXHistory> TXLists = new ArrayList<>();
        txRepo.findAll().forEach(e -> TXLists.add(e));
        return TXLists;
    }

    public Optional<TXHistory> findById(Long id) {
        Optional<TXHistory> txHistory = txRepo.findById(id);
        return txHistory;
    }

    public void deleteById(Long id) {
        txRepo.deleteById(id);
    }

    public void uploadById(Long id, TXHistory txhistory) {
        Optional<TXHistory> e = txRepo.findById(id);

        if (e.isPresent()) {
            e.get().setTxType(txhistory.getTxType());
            e.get().setAmount(txhistory.getAmount());
            e.get().setAccount(txhistory.getAccount());
            e.get().setTx_date(txhistory.getTx_date());
            txRepo.save(txhistory);
        }
    }

    public void updateById(Long id, TXHistory txHistory) {
        Optional<TXHistory> e = txRepo.findById(id);

        if (e.get().getTx_date() != txHistory.getTx_date()) {
            e.get().setTx_date(txHistory.getTx_date());
        } else if (e.get().getTxType() != txHistory.getTxType()) {
            e.get().setTxType(txHistory.getTxType());
        } else if (e.get().getAccount() != txHistory.getAccount()) {
            e.get().setAccount(txHistory.getAccount());
        } else if (e.get().getAmount() != txHistory.getAmount()) {
            e.get().setAmount(txHistory.getAmount());
        }
        txRepo.save(txHistory);
    }

    public TXHistory save(TXHistory txHistory) {
        txRepo.save(txHistory);
        return txHistory;
    }
}
