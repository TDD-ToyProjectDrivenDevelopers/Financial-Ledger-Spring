package gubun.financialledger.user.repository;

import gubun.financialledger.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndProvider(String email, String provider);
    Boolean existsByUsername(String username);
    Optional<User> findByUsernameAndEmail(String usernmae, String email);
}
