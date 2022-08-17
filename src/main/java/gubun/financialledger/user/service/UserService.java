package gubun.financialledger.user.service;

import gubun.financialledger.user.dto.RegistrationForm;
import gubun.financialledger.user.entity.User;
import gubun.financialledger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(RegistrationForm form){
        userRepository.save(form.toUser(passwordEncoder));
    }

    public Boolean isDuplicatedUser(String username){
        return userRepository.existsByUsername(username);
    }

    public Optional<User> isValidatedUser(String email) {
        return userRepository.findByEmailAndProvider(email, null);
    }
    public Optional<User> isValidatedUser(String username, String email){
        return userRepository.findByUsernameAndEmail(username, email);
    }

    @Transactional
    public void updatePassword(User user, String password){
        user.updatePassword(passwordEncoder.encode(password));
    }

    @Transactional
    public void deleteUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            User u = user.get();
            u.deleteUser(true);
            userRepository.save(u);
        }
        // 탈퇴 시 logout 진행
    }
}
