package gubun.financialledger.user.repository;

import gubun.financialledger.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // @Override
    // Optional<User> findById(String id);

    //CRUD

}
