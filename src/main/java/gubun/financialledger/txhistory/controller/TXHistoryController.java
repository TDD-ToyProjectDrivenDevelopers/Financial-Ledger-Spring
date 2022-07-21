package gubun.financialledger.txhistory.controller;

import gubun.financialledger.txhistory.entity.TXHistory;
import gubun.financialledger.txhistory.service.TXHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("main")
public class TXHistoryController {

    @Autowired
    TXHistoryService txService;

    //모든 txhistory  조회
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TXHistory>> getTXHistoryList() {
        List<TXHistory> txList = txService.findAll();
        return new ResponseEntity<List<TXHistory>>(txList, HttpStatus.OK);
    }

    //id로 거래내역 조회
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TXHistory> getTXHistory(@PathVariable("id") Long id) {
        Optional<TXHistory> txHistory = txService.findById(id);
        return new ResponseEntity<TXHistory>(txHistory.get(), HttpStatus.OK);
    }

    //id로 삭제
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteTXHistory(@PathVariable("id") Long id) {
        txService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //id로 수정 - patch용도 만들어놓음
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TXHistory> uploadTXHistory(@PathVariable Long id, TXHistory txHistory) {
        txService.updateById(id, txHistory);
        return new ResponseEntity<TXHistory>(txHistory, HttpStatus.OK);
    }

    //TXHistory 입력
    @PostMapping(value = "/saveTXHistory")
    public ResponseEntity<TXHistory> save(TXHistory txHistory) {
        return new ResponseEntity<TXHistory>(txService.save(txHistory), HttpStatus.OK);
    }
}
