package gubun.financialledger.account.controller;

import gubun.financialledger.account.dto.AccountDTO;
import gubun.financialledger.account.entity.Account;
import gubun.financialledger.account.repository.AccountRepository;
import gubun.financialledger.account.service.AccountService;
import gubun.financialledger.bank.entity.Bank;
import gubun.financialledger.bank.repository.BankRepository;
import gubun.financialledger.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.digester.ArrayStack;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    //@RequiredArgsContructor가 자동으로 생성자 주입
    private final AccountService accountService;

    private final AccountRepository accountRepository;

    private final BankRepository bankRepository;

    @GetMapping
    public String AccountList(@PageableDefault Pageable pageable, Model model){ //page , size는 fianl로 정의하여 통일감
//        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "accountName")); //pagealbe 구현체
        Page<Account> accountList = accountService.getAccountPage(pageable);
        model.addAttribute("accountList", accountList); //accountList..getContent() ??
        return "account";
    }

    @GetMapping("/new")
    public String AccountForm(Model model, @ModelAttribute("accountDTO") AccountDTO accountDTO){
        List<Bank> bankList = bankRepository.findAll();
        for(Bank bank : bankList) model.addAttribute(bank);
//        List<Bank> bank = new LinkedList<>();
//        bank.add(Bank.builder().bankName("국민은행").build());
//        bank.add(Bank.builder().bankName("우리은행").build());
        model.addAttribute("bank", bankList);

        return "accountForm";
    }

    @PostMapping
    public String saveOrder(@Valid @ModelAttribute("accountDTO") AccountDTO accountDTO, @AuthenticationPrincipal User user,
                            Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "accountForm";
        }

        accountService.saveAccount(accountDTO, user);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
