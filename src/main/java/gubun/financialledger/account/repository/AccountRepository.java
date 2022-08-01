package gubun.financialledger.account.repository;

import gubun.financialledger.account.entity.Account;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //select * from Account a left join User u
//    @EntityGraph(attributePaths = {"user"})
//    List<Account> findAccountsByUser();
}
