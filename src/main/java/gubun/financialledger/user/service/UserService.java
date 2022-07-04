package gubun.financialledger.user.service;

import gubun.financialledger.user.dto.RegistrationForm;
import gubun.financialledger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
