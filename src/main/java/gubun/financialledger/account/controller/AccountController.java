package gubun.financialledger.account.controller;

import gubun.financialledger.account.dto.AccountDTO;
import gubun.financialledger.account.entity.Account;
import gubun.financialledger.account.repository.AccountRepository;
import gubun.financialledger.account.service.AccountService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepo;

    @GetMapping("/new")
    public String AccountForm(Model model){
        List<String> bankList = accountRepo.callBankList();
        for(String bankName : bankList) model.addAttribute(bankName.toString());
        return "account";
    }

    @PostMapping
    public String saveOrder(@Valid AccountDTO accountDTO, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "account";
        }

        accountService.saveAccount(accountDTO.toEntity());
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
