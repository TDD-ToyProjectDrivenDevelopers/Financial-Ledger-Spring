package gubun.financialledger.user.oauth;

import gubun.financialledger.user.PrincipalDetails;
import gubun.financialledger.user.entity.User;
import gubun.financialledger.user.entity.UserRole;
import gubun.financialledger.user.oauth.provider.KakaoUserInfo;
import gubun.financialledger.user.oauth.provider.OAuth2UserInfo;
import gubun.financialledger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("OAuth 회원 검증");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();
        if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        // TODO : OAuth 회원이 아니라면 회원가입
        String username = oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId();

        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            log.info(provider + " 신규 회원가입");
            user = oauthRegistration(oAuth2UserInfo);
        }

        return new PrincipalDetails(user, oAuth2UserInfo);
    }

    private User oauthRegistration(OAuth2UserInfo oAuth2UserInfo){
        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = passwordEncoder.encode(UUID.randomUUID().toString());
        String email = oAuth2UserInfo.getEmail();

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(UserRole.USER)
                .isDeleted(false)
                .provider(provider)
                .providerId(providerId)
                .build();

        return userRepository.save(user);
    }
}
