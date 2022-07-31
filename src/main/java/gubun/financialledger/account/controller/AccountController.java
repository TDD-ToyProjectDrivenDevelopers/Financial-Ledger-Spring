package gubun.financialledger.account.controller;

import gubun.financialledger.account.dto.AccountDTO;
import gubun.financialledger.account.entity.Account;
import gubun.financialledger.account.repository.AccountRepository;
import gubun.financialledger.account.service.AccountService;
import gubun.financialledger.bank.entity.Bank;
import gubun.financialledger.bank.repository.BankRepository;
import gubun.financialledger.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequiredArgsConstructor
public class AccountController {
    //@RequiredArgsContructor가 자동으로 생성자 주입
    private static AccountService accountService;

    private static AccountRepository accountRepository;

    private static BankRepository bankRepository;


    @GetMapping("/new")
    public String AccountForm(Model model){
        List<Bank> bankList = bankRepository.findAll();
        for(Bank bank : bankList) model.addAttribute(bank);
        return "accountForm";
    }

    @PostMapping
    public String saveOrder(@Valid AccountDTO accountDTO, @AuthenticationPrincipal User user,
                            Bank bank, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "accontForm";
        }

        accountService.saveAccount(accountDTO.toEntity(user, bank));
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
