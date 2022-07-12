package gubun.financialledger.user.oauth.provider;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo{

    private static final String PROVIDER_ID = "id";
    private static final String PROVIDER_NAME = "kakao";

    private final Map<String, Object> attributes;

    private Map<String, Object> getKakaoAccount(){
        return (Map<String, Object>) attributes.get("kakao_account");
    }

    private Map<String, Object> getProfile(){
        return (Map<String, Object>) getKakaoAccount().get("profile");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get(PROVIDER_ID).toString();
    }

    @Override
    public String getProvider() {
        return PROVIDER_NAME;
    }

    @Override
    public String getEmail() {
        return getKakaoAccount().get("email").toString();
    }

    @Override
    public String getName() {
        return getProfile().get("nickname").toString();
    }
}
